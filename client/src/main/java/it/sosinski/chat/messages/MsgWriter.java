package it.sosinski.chat.messages;

import it.sosinski.chat.commons.channel.CurrentChannel;
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

    private CurrentChannel currentChannel;

    public MsgWriter(ManagerService managerService, CurrentChannel currentChannel) {
        this.managerService = managerService;
        this.currentChannel = currentChannel;
    }

    public void write(String name) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            while (true) {
                String text = reader.readLine();
                if (text.contains("connect")) {
                    currentChannel.setId(1L);
                } else {
                    managerService.process(currentChannel, text, name);
                }
            }
        }
    }
}
