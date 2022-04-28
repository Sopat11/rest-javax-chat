package it.sosinski.chat.channel.domain.channel;

import it.sosinski.chat.channel.domain.ports.ChannelFactory;
import it.sosinski.chat.channel.domain.ports.ChannelRepository;
import it.sosinski.chat.channel.domain.ports.ChannelService;

public class BasicChannelFactory implements ChannelFactory {

    @Override
    public ChannelService channelService(ChannelRepository channelRepository) {
        return new ChannelProcessor(channelRepository);
    }
}
