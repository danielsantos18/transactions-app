package com.example.notification_service.infrastructure.output.persistence.entity;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "notifications") // Define la colección en MongoDB
public class NotificationEntity {

    @Id
    private String id;
    private String phoneNumber;
    private String message;
    private boolean sent;
    private LocalDateTime createdAt;
    private LocalDateTime sentAt;
}
