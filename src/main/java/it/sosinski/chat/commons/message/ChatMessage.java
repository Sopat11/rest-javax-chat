package it.sosinski.chat.commons.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Builder
public class ChatMessage implements Serializable {

    @Serial
    private static final long serialVersionUID = 5010870004057592025L;

    private Long id;
    private String sender;
    private final LocalDateTime dateTime = LocalDateTime.now();
    private MessageType type;
    private String text;
    private Long channelId;
    private String fileName;
    private byte[] fileBytes;

    @Override
    public String toString() {
        return dateTime + " " + sender + ": " + text;
    }

}
