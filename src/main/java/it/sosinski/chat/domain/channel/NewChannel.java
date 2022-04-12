package it.sosinski.chat.domain.channel;

import lombok.Value;

@Value
public class NewChannel {

    String name;
    ChannelType type;
}
