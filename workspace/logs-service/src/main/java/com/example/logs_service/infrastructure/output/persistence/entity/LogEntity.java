package com.example.logs_service.infrastructure.output.persistence.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@Document(collection = "logs")
public class LogEntity {
    @Id
    private String id;
    private String service;
    private String level;
    private String message;
    private LocalDateTime timestamp;
}
