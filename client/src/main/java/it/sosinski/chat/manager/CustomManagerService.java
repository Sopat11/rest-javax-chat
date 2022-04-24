package it.sosinski.chat.manager;

import jakarta.inject.Inject;
import lombok.Setter;

@Setter
public class CustomManagerService implements ManagerService {

    @Inject
    private MassageService massageService;

    @Override
    public void process(String text, String name) {
        massageService.process(text, name);
    }
}
