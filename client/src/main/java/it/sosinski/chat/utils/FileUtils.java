package it.sosinski.chat.utils;

import it.sosinski.chat.commons.message.ChatMessage;
import it.sosinski.chat.commons.message.MessageType;
import lombok.extern.java.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;

@Log
public class FileUtils {

    private static final String FILE_DIRECTORY = "client\\files\\";

    public static void decodeFile(ChatMessage chatMessage) {
        new Thread(() -> {
            byte[] fileBytes = chatMessage.getFileBytes();
            try (FileOutputStream fos = new FileOutputStream(FILE_DIRECTORY + chatMessage.getFileName())) {
                fos.write(fileBytes);
            } catch (IOException e) {
                log.log(Level.SEVERE, "Reading file failed: " + e.getMessage());
            }
        }).start();
    }

    public static ChatMessage encodeFile(String filePath, String username, Long channelId) {
        CompletableFuture<ChatMessage> completableFuture = CompletableFuture.supplyAsync(() -> {
            Path path = Path.of("client\\" + filePath);
            String fileName = path.getFileName().toString();

            byte[] bytes = new byte[0];
            try {
                bytes = Files.readAllBytes(path);
            } catch (IOException e) {
                log.log(Level.SEVERE, "Writing file failed: " + e.getMessage());
            }
            return ChatMessage.builder()
                    .sender(username)
                    .type(MessageType.FILE)
                    .channelId(channelId)
                    .fileName(fileName)
                    .fileBytes(bytes)
                    .build();
        });

        ChatMessage chatMessage = null;
        try {
            chatMessage = completableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            log.log(Level.SEVERE, "Writing file failed: " + e.getMessage());
        }

        return chatMessage;
    }
}
