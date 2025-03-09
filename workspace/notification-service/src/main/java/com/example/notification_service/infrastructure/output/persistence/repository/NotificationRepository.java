package com.example.notification_service.infrastructure.output.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.notification_service.infrastructure.output.persistence.entity.NotificationEntity;

@Repository
public interface NotificationRepository extends CrudRepository<NotificationEntity, String> {

    Optional<NotificationEntity> findById(String id);

    List<NotificationEntity> findBySentFalse();
    
    List<NotificationEntity> allNotifications();

}