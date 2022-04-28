package it.sosinski.chat.channel.domain.ports;

public interface ChannelFactory {

    ChannelService channelService(ChannelRepository channelRepository);
}
