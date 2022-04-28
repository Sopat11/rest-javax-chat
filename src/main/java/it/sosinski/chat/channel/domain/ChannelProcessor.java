package it.sosinski.chat.channel.domain;

import it.sosinski.chat.channel.ports.ChannelRepository;
import it.sosinski.chat.channel.ports.ChannelService;
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

    @Override
    public Channel loginToChannel(Long channelId, String username) {
        return channelRepository.loginToChannel(channelId, username);
    }

    @Override
    public Channel logoutFromChannel(Long channelId, String username) {
        return channelRepository.logoutFromChannel(channelId, username);
    }
}
