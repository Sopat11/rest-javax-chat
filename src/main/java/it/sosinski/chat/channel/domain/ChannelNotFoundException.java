package it.sosinski.chat.channel.domain;

public class ChannelNotFoundException extends Throwable {

    public ChannelNotFoundException(String message) {
        super(message);
    }
}
