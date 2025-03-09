package com.example.transaction_service.infrastructure.output.persistence.repository;

import com.example.transaction_service.infrastructure.output.persistence.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<TransactionEntity, UUID> {
    List<TransactionEntity> findBySenderUsernameOrReceiverUsername(String senderUsername, String receiverUsername);
}