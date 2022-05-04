package it.sosinski.chat;

import it.sosinski.chat.commons.channel.CurrentChannel;
import it.sosinski.chat.manager.ManagerService;
import it.sosinski.chat.message.MsgListener;
import it.sosinski.chat.message.MsgWriter;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

@Log
public class Client {

    @SneakyThrows
    public static void main(String[] args) {
        final String name = args[0];
        CurrentChannel currentChannel = new CurrentChannel();

        Weld weld = new Weld();
        WeldContainer container = weld.initialize();
        ManagerService managerService = container.select(ManagerService.class).get();

        new Thread(new MsgListener(currentChannel)).start();
        new MsgWriter(managerService, currentChannel).write(name);

        container.close();
    }

}
