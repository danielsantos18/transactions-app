package com.example.notification_service.infrastructure.output.persistence.mapper;

import java.util.List;

import com.example.notification_service.domain.model.Notification;
import com.example.notification_service.infrastructure.output.persistence.entity.NotificationEntity;

public interface NotificationPersistenceMapper {
    NotificationEntity toNotificationEntity(Notification user);

    Notification toNotification(NotificationEntity entity);

    List<Notification> toNotifications(List<NotificationEntity> entityList);
}
