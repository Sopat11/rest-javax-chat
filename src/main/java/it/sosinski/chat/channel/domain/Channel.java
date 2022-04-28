package it.sosinski.chat.channel.domain;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class Channel {

    Long id;
    String name;
    ChannelType type;
    List<String> loggedUsers;

}
