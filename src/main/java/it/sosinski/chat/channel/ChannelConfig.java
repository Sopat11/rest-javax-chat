package it.sosinski.chat.channel;

import it.sosinski.chat.channel.domain.BasicChannelFactory;
import it.sosinski.chat.channel.ports.ChannelFactory;
import it.sosinski.chat.channel.ports.ChannelRepository;
import it.sosinski.chat.channel.ports.ChannelService;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

@Singleton
public class ChannelConfig {

    private static final ChannelFactory CHANNEL_FACTORY = new BasicChannelFactory();

    @Singleton
    @Produces
    public ChannelService channelService(ChannelRepository channelRepository) {
        return CHANNEL_FACTORY.channelService(channelRepository);
    }
}
