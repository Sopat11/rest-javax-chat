package it.sosinski.chat.messages;

import it.sosinski.chat.manager.ManagerService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Setter
@RequiredArgsConstructor
public class MsgWriter {

    private final ManagerService managerService;

    private Integer channelId;

    public MsgWriter(ManagerService managerService, Integer channelId) {
        this.managerService = managerService;
        this.channelId = channelId;
    }

    public void write(String name) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            while (true) {
                String text = reader.readLine();
                managerService.process(channelId, text, name);
            }
        }
    }
}
