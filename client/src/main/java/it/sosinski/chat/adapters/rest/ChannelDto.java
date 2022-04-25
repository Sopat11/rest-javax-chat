package it.sosinski.chat.adapters.rest;

import lombok.Data;

@Data
public class ChannelDto {

    Long id;
    String name;
    String type;

    @Override
    public String toString() {
        return "Kanał id: " + id + ", nazwa: " + name + ", typ: " + type;
    }
}
