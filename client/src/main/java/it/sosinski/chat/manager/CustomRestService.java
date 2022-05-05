package it.sosinski.chat.manager;

import it.sosinski.chat.channel.adapters.rest.ChannelDto;
import it.sosinski.chat.channel.adapters.rest.NewChannelDto;
import it.sosinski.chat.commons.channel.ChannelType;
import it.sosinski.chat.commons.channel.CurrentChannel;
import it.sosinski.chat.message.adapters.rest.ChatMessageDto;
import it.sosinski.chat.utils.ChannelUtils;
import it.sosinski.chat.utils.CommandsUtils;
import it.sosinski.chat.utils.ServerPrinter;
import it.sosinski.chat.utils.TextUtils;
import lombok.extern.java.Log;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.jboss.resteasy.plugins.providers.jackson.Jackson2JsonpInterceptor;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static it.sosinski.chat.manager.ServerMessagesConstants.*;

@Log
public class CustomRestService implements RestService {

    ResteasyClient restClient = new ResteasyClientBuilderImpl()
            .register(Jackson2JsonpInterceptor.class)
            .build();

    ResteasyWebTarget channels = restClient.target("http://localhost:8080/chat/api/channels");
    ResteasyWebTarget messages = restClient.target("http://localhost:8080/chat/api/messages");

    @Override
    public void process(CurrentChannel currentChannel, String text, String name) {
        // Printing channels
        if (CommandsUtils.isAskingToPrintChannels(text)) {
            printChannelsIfAny();

            // Creating channel
        } else if (CommandsUtils.isAskingToCreateChannel(text)) {
            if (!TextUtils.hasTwoParentheses(text)) {
                ServerPrinter.print(NO_CHANNEL_NAME);
                return;
            }

            NewChannelDto newChannelDto = NewChannelDto.builder()
                    .creator(name)
                    .type(getChannelType(text))
                    .name(TextUtils.getTextFromParentheses(text))
                    .build();

            ChannelDto channelDto = createChannel(newChannelDto);
            currentChannel.setId(channelDto.getId());

            // Joining channel
        } else if (CommandsUtils.isAskingToJoinChannel(text)) {
            if (!TextUtils.hasTwoParentheses(text)) {
                ServerPrinter.print(NO_CHANNEL_ID);
                return;
            }
            String channelId = TextUtils.getTextFromParentheses(text);

            Response response = channels.path("/" + channelId + "/login/" + name)
                    .request()
                    .method("PATCH");

            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                currentChannel.setId(Long.valueOf(channelId));
                ServerPrinter.print(CONNECTED_TO_SERVER);
            } else {
                String responseString = response.readEntity(String.class);
                ServerPrinter.print(responseString);
            }

            // Leaving channel
        } else if (CommandsUtils.isAskingToLeaveChannel(text)) {
            if (!ChannelUtils.isOnChannel(currentChannel)) {
                ServerPrinter.print(NOT_CONNECTED_TO_CHANNEL);
                return;
            }
            logout(currentChannel, name);

            // Printing logged users
        } else if (CommandsUtils.isAskingForLoggedUsers(text)) {
            if (!ChannelUtils.isOnChannel(currentChannel)) {
                ServerPrinter.print(NOT_CONNECTED_TO_CHANNEL);
                return;
            }

            Long channelId = ChannelUtils.getChannelId(currentChannel);
            printLoggedUsers(channelId);

            // Printing history
        } else if (CommandsUtils.isAskingToPrintHistory(text)) {
            if (!ChannelUtils.isOnChannel(currentChannel)) {
                ServerPrinter.print(NOT_CONNECTED_TO_CHANNEL);
                return;
            }

            Long channelId = ChannelUtils.getChannelId(currentChannel);
            printChatHistory(channelId);

        } else {
            ServerPrinter.print(NO_SUCH_COMMAND);
        }
    }

    private void printChannelsIfAny() {
        List<ChannelDto> channelsDto = getChannels();
        if (channelsDto.isEmpty()) {
            ServerPrinter.print(NO_CHANNELS);
        } else {
            printChannels(channelsDto);
        }
    }

    private List<ChannelDto> getChannels() {
        return channels.request()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get()
                .readEntity(new GenericType<>() {});
    }

    private ChannelDto createChannel(NewChannelDto newChannelDto) {
        return channels.request()
                .post(Entity.entity(newChannelDto, MediaType.APPLICATION_JSON))
                .readEntity(ChannelDto.class);
    }

    private String getChannelType(String text) {
        if (CommandsUtils.hasPrivateFlag(text)) {
            return ChannelType.PRIVATE.name();
        } else {
            return ChannelType.PUBLIC.name();
        }
    }

    private void logout(CurrentChannel currentChannel, String name) {
        Long channelId = currentChannel.getId();
        channels.path("/" + channelId + "/logout/" + name)
                .request()
                .method("PATCH");

        currentChannel.setId(null);
        ServerPrinter.print(LEFT_THE_CHANNEL);
    }

    private void printLoggedUsers(Long channelId) {
        List<String> loggedUsers = getLoggedUsers(channelId);
        loggedUsers.forEach(System.out::println);
    }

    private List<String> getLoggedUsers(Long channelId) {
        return channels.path("/" + channelId + "/users")
                .request()
                .get()
                .readEntity(new GenericType<>() {
                });
    }

    private void printChatHistory(Long channelId) {
        List<ChatMessageDto> chatMessages = getChatMessages(channelId);
        chatMessages.forEach(System.out::println);
    }

    private List<ChatMessageDto> getChatMessages(Long channelId) {
        return messages.path("/history/" + channelId)
                .request()
                .get()
                .readEntity(new GenericType<>() {
                });
    }

    private void printChannels(List<ChannelDto> channelsDto) {
        channelsDto.forEach(System.out::println);
    }
}
