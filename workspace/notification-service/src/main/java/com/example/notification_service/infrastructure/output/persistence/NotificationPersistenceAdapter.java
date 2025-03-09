package com.example.notification_service.infrastructure.output.persistence;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import com.example.notification_service.application.ports.output.NotificationPersistencePort;
import com.example.notification_service.domain.model.Notification;
import com.example.notification_service.infrastructure.config.TwilioConfig;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class NotificationPersistenceAdapter implements NotificationPersistencePort {

    private final TwilioConfig twilioConfig;
    private final RedisTemplate<String, Notification> redisTemplate;

    @Override
    public Notification save(Notification notification) {
        if (notification.getId() == null) {
            notification.setId(UUID.randomUUID().toString());
        }

        if (notification.getCreatedAt() == null) {
            notification.setCreatedAt(LocalDateTime.now());
        }

        if (notification.getSentAt() == null) {
            notification.setSentAt(LocalDateTime.now());
            sendMessage(notification.getPhoneNumber(), notification.getMessage());
        }

        redisTemplate.opsForValue().set(notification.getId(), notification);

        return notification;
    }

    @Override
    public List<Notification> getAllNotifications() {
        List<Notification> notifications = new ArrayList<>();
        Set<String> keys = redisTemplate.keys("*"); // Obtiene todas las claves
        if (keys != null) {
            for (String key : keys) {
                Notification notification = redisTemplate.opsForValue().get(key);
                if (notification != null) {
                    notifications.add(notification);
                }
            }
        }
        return notifications;
    }

    @Override
    public Optional<Notification> findById(String id) {
        return Optional.of(redisTemplate.opsForValue().get(id));
    }

    @Override
    public List<Notification> findUnsentNotifications() {
        Set<String> keys = redisTemplate.keys("*");

        return keys.stream()
                .map(key -> redisTemplate.opsForValue().get(key))
                .filter(notification -> notification != null && !notification.isSent())
                .collect(Collectors.toList());
    }

    @Override
    public boolean sendMessage(String phoneNumber, String info) {
        try {
            Message message = Message.creator(
                    new PhoneNumber(phoneNumber),
                    new PhoneNumber(twilioConfig.getTrialNumber()),
                    info).create();

            return message.getStatus() != Message.Status.FAILED;
        } catch (Exception e) {
            return false;
        }
    }
}
