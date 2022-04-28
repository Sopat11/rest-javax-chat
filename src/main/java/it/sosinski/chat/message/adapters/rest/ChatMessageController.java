package it.sosinski.chat.message.adapters.rest;

import it.sosinski.chat.channel.adapters.Basic;
import it.sosinski.chat.message.ports.ChatMessageService;
import lombok.Setter;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("messages")
@Setter
public class ChatMessageController {

    @Basic
    @Inject
    private ChatMessageService chatMessageService;
    @Inject
    private RestChatMessageMapper chatMessageMapper;
    @Context
    private UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/history/{channelId}")
    public Response getHistory(@PathParam("channelId") Long channelId) {
        var chatMessages = chatMessageService.getHistory(channelId);
        var chatMessagesDto = chatMessageMapper.toDto(chatMessages);

        return Response.ok(chatMessagesDto).build();
    }

}
