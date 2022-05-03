package it.sosinski.chat.channel.ports;

import it.sosinski.chat.channel.domain.Channel;

import java.util.List;

public interface ChannelRepository {

    Channel save(Channel channel);

    List<Channel> getAll();

    Channel loginToChannel(Long channelId, String username);

    boolean logoutFromChannel(Long channelId, String username);

    List<String> getLoggedUsers(Long channelId);

    boolean allowToChannel(Long channelId, String username);

    Channel getById(Long channelId);

    boolean revokeAccessFromChannel(Long channelId, String username);

    boolean isAllowed(Long channelId, String username);
}
