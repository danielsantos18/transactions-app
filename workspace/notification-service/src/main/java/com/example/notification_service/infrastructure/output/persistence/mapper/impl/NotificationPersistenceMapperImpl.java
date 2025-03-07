package com.example.notification_service.infrastructure.output.persistence.mapper.impl;

import com.example.notification_service.domain.model.Notification;
import com.example.notification_service.infrastructure.output.persistence.entity.NotificationEntity;

public class NotificationPersistenceMapperImpl {

    public Notification toModel(NotificationEntity entity) {
        
        return Notification.builder()
                .id(entity.getId())
                .phoneNumber(entity.getPhoneNumber())
                .message(entity.getMessage())
                .sent(entity.isSent())
                .createdAt(entity.getCreatedAt())
                .sentAt(entity.getSentAt())
                .build();
    }

}
