package it.sosinski.chat.manager;

public interface MassageService {

    void process(Long channelId, String text, String name);
}
