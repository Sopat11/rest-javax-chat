package it.sosinski.chat.message.adapters.persistance;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
public class JpaChatMessageRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public ChatMessageEntity save(ChatMessageEntity chatMessageEntity) {
        entityManager.persist(chatMessageEntity);
        return chatMessageEntity;
    }

}
