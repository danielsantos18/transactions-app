package com.example.notification_service.application.ports.input;

import java.util.List;

import com.example.notification_service.domain.model.Notification;

public interface NotificationServicePort {
    Notification save(Notification notification);

    List<Notification> findUnsentNotifications();

    //void markAsSent(Notification notification);

    boolean sendMessage(Notification notification);
}
