package it.sosinski.chat.manager;

public interface ManagerService {

    void process(Integer channelId, String text, String name);
}
