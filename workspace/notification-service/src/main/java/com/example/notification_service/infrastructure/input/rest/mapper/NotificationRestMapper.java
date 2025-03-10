package com.example.notification_service.infrastructure.input.rest.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.example.notification_service.domain.model.Notification;
import com.example.notification_service.infrastructure.input.rest.model.request.NotificationRequest;
import com.example.notification_service.infrastructure.input.rest.model.response.NotificationResponse;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NotificationRestMapper {

    Notification toNotification(NotificationRequest request);

    NotificationResponse toNotificationResponse(Notification notification);

    List<NotificationResponse> toNotificationResponseList(List<Notification> userList);
}
