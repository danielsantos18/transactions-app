package com.example.transaction_service.infrastructure.output.persistence.mapper.impl;

import com.example.transaction_service.domain.model.Transaction;
import com.example.transaction_service.infrastructure.output.persistence.entity.TransactionEntity;
import com.example.transaction_service.infrastructure.output.persistence.mapper.TransactionPersistenceMapper;
import java.util.UUID;

public class TransactionPersistenceMapperImpl implements TransactionPersistenceMapper {

    @Override
    public TransactionEntity toEntity(Transaction transaction) {
        return new TransactionEntity(
                transaction.getId(),
                transaction.getSenderId(),
                transaction.getReceiverId(),
                transaction.getAmount(),
                transaction.getTimestamp(),
                transaction.getStatus()
        );
    }

    @Override
    public Transaction toDomain(TransactionEntity entity) {
        return new Transaction(
                entity.getId(),
                entity.getSenderId(),
                entity.getReceiverId(),
                entity.getAmount(),
                entity.getTimestamp(),
                entity.getStatus()
        );
    }
}

