package it.sosinski.chat.channel.domain;

import it.sosinski.chat.channel.ports.ChannelFactory;
import it.sosinski.chat.channel.ports.ChannelRepository;
import it.sosinski.chat.channel.ports.ChannelService;

public class BasicChannelFactory implements ChannelFactory {

    @Override
    public ChannelService channelService(ChannelRepository channelRepository) {
        return new ChannelProcessor(channelRepository);
    }
}
