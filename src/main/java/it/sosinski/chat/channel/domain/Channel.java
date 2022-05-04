package it.sosinski.chat.channel.domain;

import lombok.Builder;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
@Builder
public class Channel {

    Long id;
    String name;
    ChannelType type;
    List<String> loggedUsers = new ArrayList<>();
    List<String> allowedUsers = new ArrayList<>();

    public void addLoggedUser(String username) {
        this.loggedUsers.add(username);
    }

    public void removeLoggedUser(String username) {
        this.loggedUsers.remove(username);
    }

    public void addAllowedUser(String username) {
        this.allowedUsers.add(username);
    }

    public void removeAllowedUser(String username) {
        this.allowedUsers.remove(username);
    }
}
