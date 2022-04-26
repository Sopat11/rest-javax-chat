package it.sosinski.chat.manager;

import it.sosinski.chat.commons.channel.CurrentChannel;
import it.sosinski.chat.utils.CommandsUtils;
import jakarta.inject.Inject;
import lombok.Setter;

@Setter
public class CustomManagerService implements ManagerService {

    @Inject
    private MassageService massageService;
    @Inject
    private RestService restService;

    @Override
    public void process(CurrentChannel currentChannel, String text, String name) {
        if (CommandsUtils.isServerCommand(text)) {
            restService.process(currentChannel, text, name);
        } else {
            massageService.process(currentChannel, text, name);
        }
    }
}
