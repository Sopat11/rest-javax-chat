package it.sosinski.chat.channel.adapters.persistance;

import it.sosinski.chat.channel.domain.ChannelType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedQuery(name = ChannelEntity.GET_ALL, query = "select ch from Channel ch")
@NamedQuery(name = ChannelEntity.GET_LOGGED_USERS, query = "select ch from Channel ch join fetch ch.loggedUsers  where ch.id =: channelId")
@Entity(name = "Channel")
@Getter
@Setter
public class ChannelEntity {

    public static final String GET_ALL = "channelGetAll";
    public static final String GET_LOGGED_USERS = "channelGetLoggedUsers";

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;
    String name;
    ChannelType type;
    @ElementCollection
    List<String> loggedUsers = new ArrayList<>();
    @ElementCollection
    List<String> allowedUsers = new ArrayList<>();

    public void addLoggedUser(String username) {
        loggedUsers.add(username);
    }

    public void removeLoggedUser(String username) {
        loggedUsers.remove(username);
    }

    public void addAllowedUser(String username) {
        allowedUsers.add(username);
    }

    public void removeAllowedUser(String username) {
        allowedUsers.remove(username);
    }

}
