package com.example.notification_service.infrastructure.output.persistence.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.notification_service.infrastructure.output.persistence.entity.NotificationEntity;

public interface NotificationRepository extends MongoRepository<NotificationEntity, String> {
    List<NotificationEntity> findBySentFalse();
}