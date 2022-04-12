package it.sosinski.chat.domain.channel;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Channel {

    Long id;
    String name;
    ChannelType type;
}
