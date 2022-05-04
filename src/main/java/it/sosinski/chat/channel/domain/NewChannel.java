package it.sosinski.chat.channel.domain;

import lombok.Value;

@Value
public class NewChannel {

    String creator;
    String name;
    ChannelType type;
}
