package com.example.notification_service.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.example.notification_service.domain.model.Notification;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Notification> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Notification> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        // Configuración de ObjectMapper con soporte para LocalDateTime
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // Registrar módulo para Java 8 Date/Time API
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        // Configurar serializador Jackson para Redis
        Jackson2JsonRedisSerializer<Notification> serializer = new Jackson2JsonRedisSerializer<>(Notification.class);
        serializer.setObjectMapper(objectMapper);

        // Definir serializadores para clave y valor
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(serializer);
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);

        template.afterPropertiesSet();
        return template;
    }
}
