package it.sosinski.chat.domain.message;

import lombok.Value;

import java.time.Instant;

@Value
public class Message {

    String sender;
    Instant time;
    MessageType type;
    String text;

}
