package it.sosinski.chat.channel.domain.channel;

import it.sosinski.chat.channel.domain.ports.ChannelRepository;
import it.sosinski.chat.channel.domain.ports.ChannelService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ChannelProcessor implements ChannelService {

    private final ChannelRepository channelRepository;

    @Override
    public List<Channel> getAll() {
        return channelRepository.getAll();
    }

    @Override
    public Channel save(NewChannel newChannel) {
        var channel = Channel.builder()
                .name(newChannel.getName())
                .type(newChannel.getType())
                .build();

        return channelRepository.save(channel);
    }
}
