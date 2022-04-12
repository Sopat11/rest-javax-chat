package it.sosinski.chat.domain.ports;

public interface ChannelFactory {

    ChannelService channelService(ChannelRepository channelRepository);
}
