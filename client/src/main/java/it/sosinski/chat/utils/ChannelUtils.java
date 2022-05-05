package it.sosinski.chat.utils;

import it.sosinski.chat.commons.channel.CurrentChannel;

public class ChannelUtils {

    public static boolean isOnChannel(CurrentChannel currentChannel) {
        return currentChannel.getId() != null;
    }

    public static Long getChannelId(CurrentChannel channel) {
        return channel.getId();
    }
}
