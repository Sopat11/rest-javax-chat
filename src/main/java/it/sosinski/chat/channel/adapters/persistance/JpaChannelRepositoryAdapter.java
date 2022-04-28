package it.sosinski.chat.channel.adapters.persistance;

import it.sosinski.chat.channel.domain.Channel;
import it.sosinski.chat.channel.ports.ChannelRepository;
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

    @Override
    public Channel loginToChannel(Long channelId, String username) {
        ChannelEntity channelEntity = channelRepository.findById(channelId);
        channelEntity.addLoggedUser(username);
        ChannelEntity updatedChannelEntity = channelRepository.update(channelEntity);

        return channelMapper.toDomain(updatedChannelEntity);
    }

    @Override
    public Channel logoutFromChannel(Long channelId, String username) {
        ChannelEntity channelEntity = channelRepository.findById(channelId);
        channelEntity.removeLoggedUser(username);
        ChannelEntity updatedChannelEntity = channelRepository.update(channelEntity);

        return channelMapper.toDomain(updatedChannelEntity);
    }

    @Override
    public List<String> getLoggedUsers(Long channelId) {
        return channelRepository.getLoggedUsers(channelId);
    }

    @Override
    public Channel allowToChannel(Long channelId, String username) {
        ChannelEntity channelEntity = channelRepository.findById(channelId);
        channelEntity.addAllowedUser(username);
        ChannelEntity updatedChannelEntity = channelRepository.update(channelEntity);

        return channelMapper.toDomain(updatedChannelEntity);
    }

    @Override
    public Channel getById(Long channelId) {
        ChannelEntity channelEntity = channelRepository.findById(channelId);
        return channelMapper.toDomain(channelEntity);
    }
}
