package it.sosinski.chat.manager;

import it.sosinski.chat.channel.adapters.rest.ChannelDto;
import it.sosinski.chat.channel.adapters.rest.NewChannelDto;
import it.sosinski.chat.commons.channel.ChannelType;
import it.sosinski.chat.commons.channel.CurrentChannel;
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

@Log
public class CustomRestService implements RestService {

    ResteasyClient restClient = new ResteasyClientBuilderImpl()
            .register(Jackson2JsonpInterceptor.class)
            .build();
    ResteasyWebTarget channels = restClient.target("http://localhost:8080/chat/api/channels");

    @Override
    public void process(CurrentChannel currentChannel, String text, String name) {
        if (CommandsUtils.isAskingToPrintChannels(text)) {

            var response = channels.request()
                    .accept(MediaType.APPLICATION_JSON_TYPE)
                    .get();

            List<ChannelDto> channelDtos = response.readEntity(new GenericType<>() {
            });
            channelDtos.forEach(System.out::println);

        } else if (CommandsUtils.isAskingToCreateChannel(text)) {
            String channelName = TextUtils.getTextFromParentheses(text);

            var newChannelDto = new NewChannelDto();
            newChannelDto.setName(channelName);
            newChannelDto.setCreator(name);

            if (CommandsUtils.hasPrivateFlag(text)) {
                newChannelDto.setType(ChannelType.PRIVATE.name());
            } else {
                newChannelDto.setType(ChannelType.PUBLIC.name());
            }

            Response response = channels.request()
                    .post(Entity.entity(newChannelDto, MediaType.APPLICATION_JSON));

            ChannelDto channelDto = response.readEntity(ChannelDto.class);
            currentChannel.setId(channelDto.getId());
        } else if (CommandsUtils.isAskingToJoinChannel(text)) {
            if (!TextUtils.hasTwoParentheses(text)) {
                ServerPrinter.print("You need to give a channel id!");
                return;
            }
            String channelId = TextUtils.getTextFromParentheses(text);

            Response response = channels.path("/" + channelId + "/login/" + name)
                    .request()
                    .method("PATCH");

            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                currentChannel.setId(Long.valueOf(channelId));
            } else {
                ServerPrinter.print("You're not allowed to join this channel!");
            }

        } else if (CommandsUtils.isAskingToLeaveChannel(text)) {
            Long channelId = currentChannel.getId();

            channels.path("/" + channelId + "/logout/" + name)
                    .request()
                    .method("PATCH");

            currentChannel.setId(null);
        } else if (CommandsUtils.isAskingForLoggedUsers(text)) {
            Long channelId = currentChannel.getId();

            if (channelId == null) {
                ServerPrinter.print("You need to connect to a channel!");
                return;
            }
            Response response = channels.path("/" + channelId + "/users")
                    .request()
                    .get();

            List<String> loggedUsers = response.readEntity(new GenericType<>() {
            });
            loggedUsers.forEach(System.out::println);

        } else {
            ServerPrinter.print("No such command!");
        }
    }
}
