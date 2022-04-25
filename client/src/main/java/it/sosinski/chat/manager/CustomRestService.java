package it.sosinski.chat.manager;

import it.sosinski.chat.adapters.rest.ChannelDto;
import it.sosinski.chat.adapters.rest.NewChannelDto;
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
    public void process(Integer channelId, String text, String name) {
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
            newChannelDto.setType("PUBLIC");

            payments.request().post(Entity.entity(newChannelDto, MediaType.APPLICATION_JSON));
        }
    }
}
