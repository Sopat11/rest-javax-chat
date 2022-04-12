package it.sosinski.chat.domain.channel;

import it.sosinski.chat.domain.ports.ChannelFactory;
import it.sosinski.chat.domain.ports.ChannelRepository;
import it.sosinski.chat.domain.ports.ChannelService;

public class BasicChannelFactory implements ChannelFactory {

    @Override
    public ChannelService channelService(ChannelRepository channelRepository) {
        return new ChannelProcessor(channelRepository);
    }
}
