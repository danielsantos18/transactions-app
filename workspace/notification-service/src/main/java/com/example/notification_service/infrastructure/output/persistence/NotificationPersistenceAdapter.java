package com.example.notification_service.infrastructure.output.persistence;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import com.example.notification_service.application.ports.output.NotificationPersistencePort;
import com.example.notification_service.domain.model.Notification;
import com.example.notification_service.infrastructure.config.TwilioConfig;
import com.example.notification_service.infrastructure.output.persistence.entity.NotificationEntity;
import com.example.notification_service.infrastructure.output.persistence.mapper.NotificationPersistenceMapper;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationPersistenceAdapter implements NotificationPersistencePort {

    private final TwilioConfig twilioConfig;
    private final NotificationPersistenceMapper notificationPersistenceMapper;
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

            try {
                sendMessage(notification.getPhoneNumber(), notification.getMessage());
                notification.setSent(true);
            } catch (Exception e) {
                log.error("Error al enviar el mensaje a {}: {}", notification.getPhoneNumber(), e.getMessage());
                notification.setSent(false); // No cambiar el estado si hubo un fallo
            }
        }

        redisTemplate.opsForValue().set(notification.getId(), notification);
        return notification;
    }

    @Override
    public List<Notification> getAllNotifications() {
        Set<String> keys = redisTemplate.keys("*");

        if (keys == null || keys.isEmpty()) {
            log.warn("No hay claves en Redis con el patrón '*'");
            return List.of();
        }

        List<NotificationEntity> notificationEntities = new ArrayList<>();

        for (String key : keys) {
            Notification notificationEntity = redisTemplate.opsForValue().get(key);
            if (notificationEntity != null) {
                NotificationEntity entity = notificationPersistenceMapper.toNotificationEntity(notificationEntity);
                notificationEntities.add(entity);
                log.info("Notificación recuperada desde Redis: {}", entity);
            } else {
                log.warn("No se encontró ningún valor en Redis para la clave: {}", key);
            }
        }

        List<NotificationEntity> dbNotifications = new ArrayList<>();
        notificationEntities.addAll(dbNotifications);

        List<Notification> notifications = notificationPersistenceMapper.toNotifications(notificationEntities);

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
    public boolean sendMessage(String phoneNumber, String messageBody) {
        try {
            Message.creator(
                    new PhoneNumber(phoneNumber),
                    new PhoneNumber(twilioConfig.getTrialNumber()),
                    messageBody).create();
            return true;
        } catch (Exception e) {
            System.err.println("Error al enviar el mensaje: " + e.getMessage());
            return false;
        }
    }
}
