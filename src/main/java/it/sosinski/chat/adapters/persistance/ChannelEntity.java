package it.sosinski.chat.adapters.persistance;

import it.sosinski.chat.domain.channel.ChannelType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@NamedQuery(name = ChannelEntity.GET_ALL, query = "select ch from Channel ch")
@Entity(name = "Channel")
@Getter
@Setter
public class ChannelEntity {

    public static final String GET_ALL = "channelGetAll";

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;
    String name;
    ChannelType type;

}
