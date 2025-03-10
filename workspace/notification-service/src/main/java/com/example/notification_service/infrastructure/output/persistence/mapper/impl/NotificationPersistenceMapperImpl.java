package com.example.notification_service.infrastructure.output.persistence.mapper.impl;

import com.example.notification_service.domain.model.Notification;
import com.example.notification_service.infrastructure.output.persistence.entity.NotificationEntity;
import com.example.notification_service.infrastructure.output.persistence.mapper.NotificationPersistenceMapper;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class NotificationPersistenceMapperImpl implements NotificationPersistenceMapper {

    @Override
    public Notification toNotification(NotificationEntity entity) {
        if (entity == null) {
            return null;
        }

        return Notification.builder()
                .id(entity.getId())
                .phoneNumber(entity.getPhoneNumber())
                .message(entity.getMessage())
                .sent(entity.isSent())
                .createdAt(entity.getCreatedAt())
                .sentAt(entity.getSentAt())
                .build();
    }

    @Override
    public NotificationEntity toNotificationEntity(Notification notification) {
        if (notification == null) {
            return null;
        }

        return NotificationEntity.builder()
                .id(notification.getId())
                .phoneNumber(notification.getPhoneNumber())
                .message(notification.getMessage())
                .sent(notification.isSent())
                .createdAt(notification.getCreatedAt())
                .sentAt(notification.getSentAt())
                .build();
    }

    @Override
    public List<Notification> toNotifications(List<NotificationEntity> entityList) {
        if (entityList == null || entityList.isEmpty()) {
            return null;
        }

        List<Notification> list = new ArrayList<>(entityList.size());
        for (NotificationEntity notificationEntity : entityList) {
            list.add(toNotification(notificationEntity));
        }

        return list;
    }
}
