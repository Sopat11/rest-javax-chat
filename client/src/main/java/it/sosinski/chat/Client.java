package it.sosinski.chat;

import it.sosinski.chat.manager.ManagerService;
import it.sosinski.chat.messages.MsgListener;
import it.sosinski.chat.messages.MsgWriter;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

@Log
public class Client {

    @SneakyThrows
    public static void main(String[] args) {
        final String name = args[0];
        Long channelId = 0L;

        Weld weld = new Weld();
        WeldContainer container = weld.initialize();
        ManagerService managerService = container.select(ManagerService.class).get();

        new Thread(new MsgListener(channelId)).start();
        new MsgWriter(managerService, channelId).write(name);

        container.close();
    }

}
