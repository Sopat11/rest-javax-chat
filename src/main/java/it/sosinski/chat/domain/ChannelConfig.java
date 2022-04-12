package it.sosinski.chat.domain;

import it.sosinski.chat.domain.channel.BasicChannelFactory;
import it.sosinski.chat.domain.ports.ChannelFactory;
import it.sosinski.chat.domain.ports.ChannelRepository;
import it.sosinski.chat.domain.ports.ChannelService;

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
