package com.example.notification_service.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.notification_service.application.ports.input.NotificationServicePort;
import com.example.notification_service.application.ports.output.NotificationPersistencePort;
import com.example.notification_service.domain.model.Notification;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService implements NotificationServicePort {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String twilioPhoneNumber;

    private final NotificationPersistencePort notificationPersistencePort;

    @Override
    public Notification save(Notification notification) {
        Notification savedNotification = notificationPersistencePort.save(notification);

        boolean sent = notificationPersistencePort.sendMessage(savedNotification.getPhoneNumber(),
                savedNotification.getMessage());

        if (sent) {
            savedNotification.setSent(true);
            savedNotification.setSentAt(java.time.LocalDateTime.now());
            notificationPersistencePort.save(savedNotification);
        }

        return savedNotification;

    }

    @Override
    public List<Notification> findUnsentNotifications() {
        return notificationPersistencePort.findUnsentNotifications();
    }

    @Override
    public boolean sendMessage(Notification notification) {
        try {
            Message message = Message.creator(
                    new PhoneNumber(notification.getPhoneNumber()),
                    new PhoneNumber(twilioPhoneNumber),
                    notification.getMessage()).create();

            return message.getStatus() != Message.Status.FAILED;
        } catch (Exception e) {
            return false;
        }
    }

}
