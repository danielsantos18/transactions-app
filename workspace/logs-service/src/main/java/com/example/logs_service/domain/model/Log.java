package com.example.logs_service.domain.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Log {
    private String id;
    private String service;
    private String level;
    private String message;
    private LocalDateTime timestamp;

    public Log(String id, String service, String level, String message, LocalDateTime timestamp) {
        this.id = id;
        this.service = service;
        this.level = level;
        this.message = message;
        this.timestamp = timestamp;
    }
}
