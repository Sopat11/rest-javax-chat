package it.sosinski.chat.channel.ports;

import it.sosinski.chat.channel.domain.Channel;
import it.sosinski.chat.channel.domain.NewChannel;

import java.util.List;

public interface ChannelService {

    List<Channel> getAll();
    Channel save(NewChannel newChannel);
    Channel loginToChannel(Long channelId, String username);
    Channel logoutFromChannel(Long channelId, String username);
}
