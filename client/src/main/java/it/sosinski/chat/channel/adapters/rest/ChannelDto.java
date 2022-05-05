package it.sosinski.chat.channel.adapters.rest;

import lombok.Data;

@Data
public class ChannelDto {

    Long id;
    String name;
    String type;

    @Override
    public String toString() {
        return "Channel id: " + id + ", name: " + name + ", type: " + type.toLowerCase();
    }
}
