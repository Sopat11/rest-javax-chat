package it.sosinski.chat.channel.adapters.rest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewChannelDto {

    private String creator;
    private String name;
    private String type;

}
