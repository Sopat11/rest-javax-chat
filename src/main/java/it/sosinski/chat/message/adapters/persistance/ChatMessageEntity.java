package it.sosinski.chat.message.adapters.persistance;

import it.sosinski.chat.commons.message.MessageType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NamedQuery(name = ChatMessageEntity.GET_HISTORY, query = "select cht FROM ChatMessage cht where cht.channelId =: channelId order by cht.dateTime asc")
@Entity(name = "ChatMessage")
@Getter
@Setter
public class ChatMessageEntity {

    public static final String GET_HISTORY = "chatMessagesGetHistory";

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String sender;
    private LocalDateTime dateTime;
    private MessageType type;
    private String text;
    private Long channelId;

}
