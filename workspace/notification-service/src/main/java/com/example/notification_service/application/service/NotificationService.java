package com.example.notification_service.application.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.notification_service.application.ports.input.NotificationServicePort;
import com.example.notification_service.application.ports.output.NotificationPersistencePort;
import com.example.notification_service.domain.exception.NotFoundException;
import com.example.notification_service.domain.model.Notification;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService implements NotificationServicePort {

    private final NotificationPersistencePort notificationPersistencePort;

    public Notification save(Notification notification) {
        return notificationPersistencePort.save(notification);
    }

    public Notification getNotification(String id) {
        return notificationPersistencePort.findById(id)
                .orElseThrow(() -> new NotFoundException("Notification with ID " + id + " not found"));
    }

    @Override
    public List<Notification> findUnsentNotifications() {
        return notificationPersistencePort.findUnsentNotifications();
    }

    @Override
    public boolean sendMessage(Notification notification) {
        return notificationPersistencePort.sendMessage(notification.getPhoneNumber(), notification.getMessage());
    }

    @Override
    public List<Notification> getNotifications() {
        return notificationPersistencePort.getAllNotifications();
    }
}
