package user.publisher;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import user.dto.SkillOfferDto;

@Component
public class EventSkillOfferedPublisher extends AbstractPublisher<SkillOfferDto> {
    public EventSkillOfferedPublisher(RedisTemplate<String, Object> redisTemplate,
                                      ObjectMapper jsonMapper,
                                      @Value("${spring.data.redis.channels.skill-offered-channel.name}") String channel) {
        super(redisTemplate, jsonMapper, channel);
    }
}