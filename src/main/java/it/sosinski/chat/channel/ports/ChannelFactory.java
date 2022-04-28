package it.sosinski.chat.channel.ports;

public interface ChannelFactory {

    ChannelService channelService(ChannelRepository channelRepository);
}
