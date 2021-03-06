package it.sosinski.chat.channel.adapters.rest;

import it.sosinski.chat.channel.domain.ChannelType;
import lombok.Data;

@Data
public class ChannelDto {

    Long id;
    String name;
    ChannelType type;
}
