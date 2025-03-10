package com.example.notification_service.infrastructure.output.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.notification_service.domain.model.Notification;
import com.example.notification_service.infrastructure.output.persistence.entity.NotificationEntity;

@Mapper(componentModel = "spring")
public interface NotificationPersistenceMapper {
    NotificationEntity toNotificationEntity(Notification notification);

    Notification toNotification(NotificationEntity entity);

    List<Notification> toNotifications(List<NotificationEntity> entityList);
}
