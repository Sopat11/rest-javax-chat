package it.sosinski.chat.manager;

import it.sosinski.chat.commons.channel.CurrentChannel;

public interface RestService {

    void process(CurrentChannel currentChannel, String text, String name);

}
