package com.example.notification_service.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.example.notification_service.domain.model.Notification;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Notification> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Notification> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        // Configura el ObjectMapper para manejar LocalDateTime
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        // Configura el serializador
        Jackson2JsonRedisSerializer<Notification> serializer = new Jackson2JsonRedisSerializer<>(Notification.class);
        serializer.setObjectMapper(objectMapper);

        template.setValueSerializer(serializer);
        return template;
    }
}
