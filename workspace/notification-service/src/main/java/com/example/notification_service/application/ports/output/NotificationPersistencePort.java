package com.example.notification_service.application.ports.output;

import java.util.List;

import com.example.notification_service.domain.model.Notification;

public interface NotificationPersistencePort {
    Notification save(Notification notification);

    List<Notification> findUnsentNotifications();

    void markAsSent(Notification notification);

    boolean sendMessage(Notification notification);
}
