package it.sosinski.chat.message.adapters.persistance;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Singleton
public class JpaChatMessageRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public ChatMessageEntity save(ChatMessageEntity chatMessageEntity) {
        entityManager.persist(chatMessageEntity);
        return chatMessageEntity;
    }

    public List<ChatMessageEntity> getHistory(Long channelId) {
        return entityManager.createNamedQuery(ChatMessageEntity.GET_HISTORY, ChatMessageEntity.class)
                .setParameter("channelId", channelId)
                .getResultList();
    }
}
