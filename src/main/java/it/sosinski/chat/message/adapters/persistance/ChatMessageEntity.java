package it.sosinski.chat.message.adapters.persistance;

import it.sosinski.chat.commons.message.MessageType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity(name = "ChatMessage")
@Getter
@Setter
public class ChatMessageEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String sender;
    private LocalDateTime dateTime;
    private MessageType type;
    private String text;
    private Long channelId;

}
