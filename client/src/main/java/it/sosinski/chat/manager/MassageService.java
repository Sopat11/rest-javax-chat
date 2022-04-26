package it.sosinski.chat.manager;

import it.sosinski.chat.commons.channel.CurrentChannel;

public interface MassageService {

    void process(CurrentChannel currentChannel, String text, String name);
}
