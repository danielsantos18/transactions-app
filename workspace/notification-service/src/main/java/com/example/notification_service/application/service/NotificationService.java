package com.example.notification_service.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.notification_service.domain.model.Notification;
import com.example.notification_service.infrastructure.output.persistence.mapper.NotificationPersistenceMapper;
import com.example.notification_service.infrastructure.output.persistence.repository.NotificationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationPersistenceMapper mapper;

    // Guardar una notificación en MongoDB
    public Notification saveNotification(Notification notification) {
        return mapper.toNotification(notificationRepository.save(mapper.toNotificationEntity(notification)));
    }

    // Obtener notificaciones pendientes de envío
    public List<Notification> getPendingNotifications() {
        return notificationRepository.findBySentFalse().stream()
                .map(mapper::toNotification)
                .collect(Collectors.toList());
    }

    // Marcar una notificación como enviada
    public void markAsSent(Notification notification) {
        notification.setSent(true);
        notificationRepository.save(mapper.toNotificationEntity(notification));
    }
}
