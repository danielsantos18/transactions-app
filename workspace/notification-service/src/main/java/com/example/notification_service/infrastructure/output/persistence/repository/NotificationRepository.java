package com.example.notification_service.infrastructure.output.persistence.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.notification_service.infrastructure.output.persistence.entity.NotificationEntity;

@Repository
public interface NotificationRepository {

    List<NotificationEntity> findBySentFalse();
}