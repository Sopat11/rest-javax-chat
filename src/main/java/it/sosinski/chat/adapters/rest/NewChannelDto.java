package it.sosinski.chat.adapters.rest;

import it.sosinski.chat.commons.validator.ChannelTypeV;
import lombok.Data;

@Data
public class NewChannelDto {

    private String name;
    @ChannelTypeV
    private String type;

}
