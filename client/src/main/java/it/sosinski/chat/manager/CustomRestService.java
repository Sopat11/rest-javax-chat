package it.sosinski.chat.manager;

import it.sosinski.chat.channel.adapters.rest.ChannelDto;
import it.sosinski.chat.channel.adapters.rest.NewChannelDto;
import it.sosinski.chat.commons.channel.ChannelType;
import it.sosinski.chat.commons.channel.CurrentChannel;
import it.sosinski.chat.utils.CommandsUtils;
import it.sosinski.chat.utils.TextUtils;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.jboss.resteasy.plugins.providers.jackson.Jackson2JsonpInterceptor;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class CustomRestService implements RestService {

    ResteasyClient restClient = new ResteasyClientBuilderImpl()
            .register(Jackson2JsonpInterceptor.class)
            .build();
    ResteasyWebTarget payments = restClient.target("http://localhost:8080/chat/api/channels");

    @Override
    public void process(CurrentChannel currentChannel, String text, String name) {
        if (CommandsUtils.isAskingToPrintChannels(text)) {

            var response = payments.request()
                    .accept(MediaType.APPLICATION_JSON_TYPE)
                    .get();

            List<ChannelDto> channelDtos = response.readEntity(new GenericType<>() {
            });
            channelDtos.forEach(System.out::println);

        } else if (CommandsUtils.isAskingToCreateChannel(text)) {
            String channelName = TextUtils.getTextFromParentheses(text);

            var newChannelDto = new NewChannelDto();
            newChannelDto.setName(channelName);

            if (CommandsUtils.hasPrivateFlag(text)) {
                newChannelDto.setType(ChannelType.PRIVATE.name());
            } else {
                newChannelDto.setType(ChannelType.PUBLIC.name());
            }

            payments.request().post(Entity.entity(newChannelDto, MediaType.APPLICATION_JSON));
        }
    }
}
