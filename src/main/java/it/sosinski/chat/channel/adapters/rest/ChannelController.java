package it.sosinski.chat.channel.adapters.rest;

import it.sosinski.chat.channel.adapters.Basic;
import it.sosinski.chat.channel.domain.ports.ChannelService;
import lombok.Setter;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

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

    private URI getLocation(Long id) {
        return uriInfo.getAbsolutePathBuilder().path(String.valueOf(id)).build();
    }
}