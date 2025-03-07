package com.example.notification_service.domain.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    private String id;
    private String phoneNumber;
    private String message;
    private boolean sent;
    private LocalDateTime createdAt;
    private LocalDateTime sentAt;
}
