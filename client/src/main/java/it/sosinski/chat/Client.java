package it.sosinski.chat;

import it.sosinski.chat.messages.MsgListener;
import it.sosinski.chat.messages.MsgWriter;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

@Log
public class Client {

    @SneakyThrows
    public static void main(String[] args) {
        final String name = args[0];

        new Thread(new MsgListener()).start();
        new MsgWriter().write(name);

    }

}
