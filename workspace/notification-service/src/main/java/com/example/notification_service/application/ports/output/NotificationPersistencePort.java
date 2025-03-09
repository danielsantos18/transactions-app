package com.example.notification_service.application.ports.output;

import java.util.List;
import java.util.Optional;

import com.example.notification_service.domain.model.Notification;

public interface NotificationPersistencePort {

    Notification save(Notification notification);

    List<Notification> findUnsentNotifications();

    // void markAsSent(Notification notification);

    boolean sendMessage(String phoneNumber, String Message);

    Optional<Notification> findById(String id);

    List<Notification> getAllNotifications();
}
