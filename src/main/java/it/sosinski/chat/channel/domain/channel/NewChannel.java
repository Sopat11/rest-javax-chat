package it.sosinski.chat.channel.domain.channel;

import lombok.Value;

@Value
public class NewChannel {

    String name;
    ChannelType type;
}
