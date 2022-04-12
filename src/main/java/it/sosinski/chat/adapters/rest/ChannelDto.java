package it.sosinski.chat.adapters.rest;

import it.sosinski.chat.domain.channel.ChannelType;
import lombok.Data;

@Data
public class ChannelDto {

    Long id;
    String name;
    ChannelType type;
}
