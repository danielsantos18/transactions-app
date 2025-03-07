package com.example.notification_service.infrastructure.input.rest.model.request;

import lombok.Data;

@Data
public class NotificationRequest {
    private String phoneNumber;
    private String message;
}
