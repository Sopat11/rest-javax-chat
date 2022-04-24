package it.sosinski.chat.messages;

import it.sosinski.chat.manager.ManagerService;
import lombok.Setter;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Setter
public class MsgWriter {

    public void write(String name) throws IOException {

        Weld weld = new Weld();
        WeldContainer container = weld.initialize();
        ManagerService managerService = container.select(ManagerService.class).get();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            while (true) {
                String text = reader.readLine();
                managerService.process(text, name);
            }
        }
    }
}
