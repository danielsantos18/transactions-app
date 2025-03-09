package com.example.notification_service.infrastructure.output.persistence;

import org.springframework.stereotype.Component;
import com.example.notification_service.application.ports.output.NotificationPersistencePort;
import com.example.notification_service.domain.model.Notification;
import com.example.notification_service.infrastructure.output.persistence.entity.NotificationEntity;
import com.example.notification_service.infrastructure.output.persistence.mapper.NotificationPersistenceMapper;
import com.example.notification_service.infrastructure.output.persistence.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class NotificationPersistenceAdapter implements NotificationPersistencePort {

    private final NotificationRepository repository;
    private final NotificationPersistenceMapper mapper;

    @Override
    public Notification save(Notification notification) {
        NotificationEntity entity = mapper.toNotificationEntity(notification);
        return mapper.toNotification(repository.save(entity));
    }

    @Override
    public Optional<Notification> findById(String id) {
        return repository.findById(id).map(mapper::toNotification);
    }

    @Override
    public List<Notification> findUnsentNotifications() {
        return repository.findBySentFalse()
                .stream()
                .map(mapper::toNotification)
                .collect(Collectors.toList());
    }

    @Override
    public boolean sendMessage(String phoneNumber, String message) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sendMessage'");
    }
}
