package it.sosinski.chat.manager;

public interface ManagerService {

    void process(Long channelId, String text, String name);
}
