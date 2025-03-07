package com.example.notification_service.infrastructure.input.rest.mapper;

import org.mapstruct.Mapper;

import com.example.notification_service.domain.model.Notification;
import com.example.notification_service.infrastructure.input.rest.model.request.NotificationRequest;
import com.example.notification_service.infrastructure.input.rest.model.response.NotificationResponse;

@Mapper
public interface NotificationRestMapper {
    Notification toNotification(NotificationRequest request);
    NotificationResponse toNotificationResponse(Notification notification);

}
