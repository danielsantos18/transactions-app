package com.example.transaction_service.application.service;

import com.example.transaction_service.application.ports.input.TransactionServicePort;
import com.example.transaction_service.application.ports.output.TransactionPersistencePort;
import com.example.transaction_service.domain.model.Transaction;
import com.example.transaction_service.infrastructure.clients.UserClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List; // Importación agregada
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService implements TransactionServicePort {

    private final UserClient userClient;
    private final TransactionPersistencePort transactionPersistencePort;

    public TransactionService(UserClient userClient, TransactionPersistencePort transactionPersistencePort) {
        this.userClient = userClient;
        this.transactionPersistencePort = transactionPersistencePort;
    }

    @Transactional
    public String transferMoney(String senderUsername, String receiverUsername, BigDecimal amount) {
        if (!userClient.userExists(senderUsername) || !userClient.userExists(receiverUsername)) {
            return "Uno o ambos usuarios no existen.";
        }

        BigDecimal senderBalance = userClient.getUserBalance(senderUsername);
        if (senderBalance.compareTo(amount) < 0) {
            return "Saldo insuficiente.";
        }

        BigDecimal newSenderBalance = senderBalance.subtract(amount);
        BigDecimal receiverBalance = userClient.getUserBalance(receiverUsername).add(amount);

        userClient.updateUserBalance(senderUsername, newSenderBalance);
        userClient.updateUserBalance(receiverUsername, receiverBalance);

        Transaction transaction = new Transaction(senderUsername, receiverUsername, amount);
        transactionPersistencePort.saveTransaction(transaction);

        return "Transferencia realizada con éxito.";
    }

    public Transaction createTransaction(String senderUsername, String receiverUsername, BigDecimal amount) {
        Transaction transaction = new Transaction(senderUsername, receiverUsername, amount);
        return transactionPersistencePort.saveTransaction(transaction);
    }

    public Optional<Transaction> getTransactionById(UUID id) {
        return Optional.ofNullable(transactionPersistencePort.getTransactionById(id));
    }

    public List<Transaction> getAllTransactions() {
        return transactionPersistencePort.getAllTransactions();
    }
}
