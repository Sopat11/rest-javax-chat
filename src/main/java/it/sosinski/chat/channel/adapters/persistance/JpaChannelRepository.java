package it.sosinski.chat.channel.adapters.persistance;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Singleton
public class JpaChannelRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public ChannelEntity save(ChannelEntity channelEntity) {
        entityManager.persist(channelEntity);
        return channelEntity;
    }

    public ChannelEntity update(ChannelEntity channelEntity) {
        ChannelEntity mergedChannelEntity = entityManager.merge(channelEntity);
        return mergedChannelEntity;
    }

    public List<ChannelEntity> getAll() {
        var result = entityManager.createNamedQuery(ChannelEntity.GET_ALL, ChannelEntity.class)
                .getResultList();
        return result;
    }

    public ChannelEntity findById(Long channelId) {
        return entityManager.find(ChannelEntity.class, channelId);
    }
}
