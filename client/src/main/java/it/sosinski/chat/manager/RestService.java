package it.sosinski.chat.manager;

public interface RestService {

    void process(Long channelId, String text, String name);

}
