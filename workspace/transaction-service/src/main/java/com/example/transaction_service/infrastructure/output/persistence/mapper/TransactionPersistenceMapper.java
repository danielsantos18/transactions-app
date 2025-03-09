package com.example.transaction_service.infrastructure.output.persistence.mapper;

import com.example.transaction_service.domain.model.Transaction;
import com.example.transaction_service.infrastructure.output.persistence.entity.TransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionPersistenceMapper {
    TransactionPersistenceMapper INSTANCE = Mappers.getMapper(TransactionPersistenceMapper.class);

    @Mapping(source = "senderId", target = "senderId")
    @Mapping(source = "receiverId", target = "receiverId")
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "timestamp", target = "timestamp")
    TransactionEntity toEntity(Transaction transaction);

    @Mapping(source = "senderId", target = "senderId")
    @Mapping(source = "receiverId", target = "receiverId")
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "timestamp", target = "timestamp")
    Transaction toDomain(TransactionEntity transactionEntity);
}
