package it.sosinski.chat.channel.adapters.rest;

import it.sosinski.chat.channel.adapters.Basic;
import it.sosinski.chat.channel.ports.ChannelService;
import lombok.Setter;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("channels")
@Setter
public class ChannelController {

    @Basic
    @Inject
    private ChannelService channelService;
    @Inject
    private RestChannelMapper channelMapper;
    @Context
    private UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        var channels = channelService.getAll();
        var channelsDto = channelMapper.toDto(channels);
        return Response.ok(channelsDto).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid NewChannelDto newChannelDto) {
        var channel = channelMapper.toDomain(newChannelDto);
        var savedChannel = channelService.save(channel);
        var channelDto = channelMapper.toDto(savedChannel);
        return Response.created(getLocation(channelDto.getId()))
                .entity(channelDto)
                .build();
    }

    @PATCH
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{channelId}/login/{username}")
    public Response loginToChannel(@PathParam("channelId") Long channelId,
                                   @PathParam("username") String userName) {

        //TODO: Sprawdzić, czy jest allowed dla kanału prywatnego
        var channel = channelService.loginToChannel(channelId, userName);
        var channelDto = channelMapper.toDto(channel);
        return Response.created(getLocation(channelDto.getId()))
                .entity(channelDto)
                .build();
    }

    @PATCH
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{channelId}/logout/{username}")
    public Response logoutFromChannel(@PathParam("channelId") Long channelId,
                                      @PathParam("username") String userName) {

        var channel = channelService.logoutFromChannel(channelId, userName);
        var channelDto = channelMapper.toDto(channel);
        return Response.created(getLocation(channelDto.getId()))
                .entity(channelDto)
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{channelId}/users")
    public Response getLoggedUsers(@PathParam("channelId") Long channelId) {

        List<String> users = channelService.getLoggedUsers(channelId);

        return Response.ok(users).build();
    }

    private URI getLocation(Long id) {
        return uriInfo.getAbsolutePathBuilder().path(String.valueOf(id)).build();
    }

    @PATCH
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{channelId}/allow/{username}")
    public Response allowToChannel(@PathParam("channelId") Long channelId,
                                   @PathParam("username") String username) {

        var channel = channelService.allowToChannel(channelId, username);
        if (channel == null) {
            String error = "You can not allow user to the PUBLIC channel!";
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode())
                    .entity(error)
                    .build();
        }

        var channelDto = channelMapper.toDto(channel);
        return Response.created(getLocation(channelDto.getId()))
                .entity(channelDto)
                .build();
    }

    @PATCH
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{channelId}/revoke/{username}")
    public Response revokeAccessFromChannelFromChannel(@PathParam("channelId") Long channelId,
                                    @PathParam("username") String username) {

        boolean success = channelService.revokeAccessFromChannelFromChannel(channelId, username);

        if (success) {
            return Response.ok()
                    .entity("Access to the channel for user " + username + " revoked.")
                    .build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode())
                    .entity("User " + username + " is not already allowed to the channel.")
                    .build();
        }
    }

}
