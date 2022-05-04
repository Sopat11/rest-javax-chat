package it.sosinski.chat.channel.ports;

import it.sosinski.chat.channel.domain.Channel;
import it.sosinski.chat.channel.domain.NewChannel;

import java.util.List;

public interface ChannelService {

    List<Channel> getAll();
    Channel create(NewChannel newChannel);
    boolean loginToChannel(Long channelId, String username);
    boolean logoutFromChannel(Long channelId, String username);
    List<String> getLoggedUsers(Long channelId);
    boolean allowToChannel(Long channelId, String username);
    boolean revokeAccessFromChannelFromChannel(Long channelId, String username);
}
