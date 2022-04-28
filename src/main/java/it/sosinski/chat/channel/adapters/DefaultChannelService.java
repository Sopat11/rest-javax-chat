package it.sosinski.chat.channel.adapters;

import it.sosinski.chat.channel.domain.channel.Channel;
import it.sosinski.chat.channel.domain.channel.NewChannel;
import it.sosinski.chat.channel.domain.ports.ChannelService;
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
}