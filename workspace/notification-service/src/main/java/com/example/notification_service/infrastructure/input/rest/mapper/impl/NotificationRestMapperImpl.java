package com.example.notification_service.infrastructure.input.rest.mapper.impl;

import org.springframework.stereotype.Component;
import com.example.notification_service.domain.model.Notification;
import com.example.notification_service.infrastructure.input.rest.mapper.NotificationRestMapper;
import com.example.notification_service.infrastructure.input.rest.model.request.NotificationRequest;
import com.example.notification_service.infrastructure.input.rest.model.response.NotificationResponse;

@Component
public class NotificationRestMapperImpl implements NotificationRestMapper {

    @Override
    public Notification toNotification(NotificationRequest request) {

        if (request == null) {
            return null;
        }

        Notification.NotificationBuilder notification = Notification.builder();

        notification.phoneNumber(request.getPhoneNumber());
        notification.message(request.getMessage());

        return notification.build();
    }

    @Override
    public NotificationResponse toNotificationResponse(Notification notification) {
        if (notification == null) {
            return null;
        }

        NotificationResponse.NotificationResponseBuilder notificationResponse = NotificationResponse.builder();

        notificationResponse.id(notification.getId());
        notificationResponse.phoneNumber(notification.getPhoneNumber());
        notificationResponse.message(notification.getMessage());
        notificationResponse.sent(notification.isSent());
        notificationResponse.createdAt(notification.getCreatedAt());
        notificationResponse.sentAt(notification.getSentAt());

        return notificationResponse.build();
    }
}
