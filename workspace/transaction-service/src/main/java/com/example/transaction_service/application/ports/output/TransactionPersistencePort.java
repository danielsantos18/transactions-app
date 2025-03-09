package com.example.transaction_service.application.ports.output;

import com.example.transaction_service.domain.model.Transaction;
import java.util.List;
import java.util.UUID;

public interface TransactionPersistencePort {
    Transaction saveTransaction(Transaction transaction);
    Transaction getTransactionById(UUID transactionId);
    List<Transaction> getTransactionsByUserId(UUID userId);
}

