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
    public Channel create(NewChannel newChannel) {
        var username = newChannel.getCreator();
        var channel = Channel.builder()
                .name(newChannel.getName())
                .type(newChannel.getType())
                .build();

        channel.addLoggedUser(username);

        if (channel.getType() == ChannelType.PRIVATE) {
            channel.addAllowedUser(username);
        }

        return channelRepository.save(channel);
    }

    @Override
    public boolean loginToChannel(Long channelId, String username) {
        boolean isAllowed = channelRepository.isAllowed(channelId, username);

        if (isAllowed) {
            channelRepository.loginToChannel(channelId, username);
        }
        return isAllowed;
    }

    @Override
    public boolean logoutFromChannel(Long channelId, String username) {
        return channelRepository.logoutFromChannel(channelId, username);
    }

    @Override
    public List<String> getLoggedUsers(Long channelId) {
        return channelRepository.getLoggedUsers(channelId);
    }

    @Override
    public boolean allowToChannel(Long channelId, String username) {
        return channelRepository.allowToChannel(channelId, username);
    }

    @Override
    public boolean revokeAccessFromChannelFromChannel(Long channelId, String username) {
        return channelRepository.revokeAccessFromChannel(channelId, username);
    }
}
