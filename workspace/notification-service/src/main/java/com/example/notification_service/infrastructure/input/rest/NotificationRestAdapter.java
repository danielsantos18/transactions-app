package com.example.notification_service.infrastructure.input.rest;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.notification_service.application.ports.input.NotificationServicePort;
import com.example.notification_service.domain.model.Notification;
import com.example.notification_service.infrastructure.input.rest.mapper.NotificationRestMapper;
import com.example.notification_service.infrastructure.input.rest.model.request.NotificationRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationRestAdapter {

    private final NotificationServicePort notificationServicePort;
    private final NotificationRestMapper mapper;

    @PostMapping("/sent")
    public ResponseEntity<Notification> sendNotification(@RequestBody NotificationRequest request) {
        Notification notification = mapper.toNotification(request);
        return ResponseEntity.ok(notificationServicePort.save(notification));
    }

    @GetMapping("/pending")
    public ResponseEntity<List<Notification>> getPendingNotifications() {
        return ResponseEntity.ok(notificationServicePort.getNotifications());
    }
}
