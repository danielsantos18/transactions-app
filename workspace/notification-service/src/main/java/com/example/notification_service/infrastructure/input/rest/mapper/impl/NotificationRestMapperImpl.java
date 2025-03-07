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
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
