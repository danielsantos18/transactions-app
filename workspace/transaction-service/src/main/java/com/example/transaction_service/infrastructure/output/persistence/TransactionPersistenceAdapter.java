package com.example.transaction_service.infrastructure.output.persistence;

import com.example.transaction_service.application.ports.output.TransactionPersistencePort;
import com.example.transaction_service.domain.model.Transaction;
import com.example.transaction_service.infrastructure.output.persistence.entity.TransactionEntity;
import com.example.transaction_service.infrastructure.output.persistence.mapper.TransactionPersistenceMapper;
import com.example.transaction_service.infrastructure.output.persistence.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransactionPersistenceAdapter implements TransactionPersistencePort {

    private final TransactionRepository transactionRepository;
    private final TransactionPersistenceMapper transactionMapper;

    public TransactionPersistenceAdapter(TransactionRepository transactionRepository, TransactionPersistenceMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        TransactionEntity entity = transactionMapper.toEntity(transaction);
        return transactionMapper.toDomain(transactionRepository.save(entity));
    }

    @Override
    public Transaction getTransactionById(UUID id) {
        return transactionRepository.findById(id)
                .map(transactionMapper::toDomain)
                .orElse(null);
    }

    @Override
    public List<Transaction> getTransactionsByUsername(String username) {
        return transactionRepository.findBySenderUsernameOrReceiverUsername(username, username)
                .stream()
                .map(transactionMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
public List<Transaction> getAllTransactions() {
    return transactionRepository.findAll()
            .stream()
            .map(transactionMapper::toDomain)
            .collect(Collectors.toList());
}
}