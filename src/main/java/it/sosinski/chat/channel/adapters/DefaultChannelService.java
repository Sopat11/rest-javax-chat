package it.sosinski.chat.channel.adapters;

import it.sosinski.chat.channel.domain.Channel;
import it.sosinski.chat.channel.domain.NewChannel;
import it.sosinski.chat.channel.ports.ChannelService;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import java.util.List;

@Basic
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class DefaultChannelService implements ChannelService {

    private final ChannelService channelService;

    @Override
    public List<Channel> getAll() {
        return channelService.getAll();
    }

    @Override
    public Channel save(NewChannel newChannel) {
        return channelService.save(newChannel);
    }

    @Override
    public Channel loginToChannel(Long channelId, String username) {
        return channelService.loginToChannel(channelId, username);
    }

    @Override
    public Channel logoutFromChannel(Long channelId, String username) {
        return channelService.logoutFromChannel(channelId, username);
    }

    @Override
    public List<String> getLoggedUsers(Long channelId) {
        return channelService.getLoggedUsers(channelId);
    }

    @Override
    public boolean allowToChannel(Long channelId, String username) {
        return channelService.allowToChannel(channelId, username);
    }

    @Override
    public boolean revokeAccessFromChannelFromChannel(Long channelId, String username) {
        return channelService.revokeAccessFromChannelFromChannel(channelId, username);
    }
}
