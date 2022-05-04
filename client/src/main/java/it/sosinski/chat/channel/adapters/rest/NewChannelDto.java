package it.sosinski.chat.channel.adapters.rest;

import lombok.Data;

@Data
public class NewChannelDto {

    private String creator;
    private String name;
    private String type;

}
