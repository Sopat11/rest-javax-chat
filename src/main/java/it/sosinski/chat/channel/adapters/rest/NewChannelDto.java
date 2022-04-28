package it.sosinski.chat.channel.adapters.rest;

import it.sosinski.chat.channel.commons.validator.ChannelTypeV;
import lombok.Data;

@Data
public class NewChannelDto {

    private String name;
    @ChannelTypeV
    private String type;

}
