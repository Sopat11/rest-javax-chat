package it.sosinski.chat.adapters.persistance;

import it.sosinski.chat.domain.channel.Channel;
import it.sosinski.chat.domain.ports.ChannelRepository;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Singleton
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class JpaChannelRepositoryAdapter implements ChannelRepository {

    private final JpaChannelRepository channelRepository;
    private final JpaPersistenceChannelMapper channelMapper;

    @Override
    public Channel save(Channel channel) {
        var channelEntity = channelMapper.toEntity(channel);
        var savedEntity = channelRepository.save(channelEntity);
        return channelMapper.toDomain(savedEntity);
    }

    @Override
    public List<Channel> getAll() {
        var channels = channelRepository.getAll();
        return channels.stream().map(channelMapper::toDomain).toList();
    }
}
