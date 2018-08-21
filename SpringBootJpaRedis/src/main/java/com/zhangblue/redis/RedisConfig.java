package com.zhangblue.redis;

import com.zhangblue.jpa.entity.User;
import java.net.UnknownHostException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
public class RedisConfig {

  @Bean
  public RedisTemplate<Object, User> userRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
    RedisTemplate<Object, User> template = new RedisTemplate<Object,User>();
    template.setConnectionFactory(redisConnectionFactory);
    Jackson2JsonRedisSerializer<User> userJackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(User.class);
    template.setDefaultSerializer(userJackson2JsonRedisSerializer);
    return template;
  }
}
