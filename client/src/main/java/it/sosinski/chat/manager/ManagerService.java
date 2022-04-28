package it.sosinski.chat.manager;

import it.sosinski.chat.commons.channel.CurrentChannel;

public interface ManagerService {

    void process(CurrentChannel currentChannel, String text, String name);
}
