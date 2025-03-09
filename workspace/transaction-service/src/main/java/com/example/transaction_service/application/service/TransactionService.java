package com.example.transaction_service.application.service;

import com.example.transaction_service.application.ports.input.TransactionServicePort;
import com.example.transaction_service.application.ports.output.TransactionPersistencePort;
import com.example.transaction_service.domain.model.Transaction;
import com.example.transaction_service.infrastructure.clients.UserClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
    public String transferMoney(UUID senderId, UUID receiverId, BigDecimal amount) {
        if (!userClient.userExists(senderId) || !userClient.userExists(receiverId)) {
            return "Uno o ambos usuarios no existen.";
        }

        BigDecimal senderBalance = userClient.getUserBalance(senderId);
        if (senderBalance.compareTo(amount) < 0) {
            return "Saldo insuficiente.";
        }

        BigDecimal newSenderBalance = senderBalance.subtract(amount);
        BigDecimal receiverBalance = userClient.getUserBalance(receiverId).add(amount);

        userClient.updateUserBalance(senderId, newSenderBalance);
        userClient.updateUserBalance(receiverId, receiverBalance);

        // ✅ Se usa el constructor correcto con tres parámetros
        Transaction transaction = new Transaction(senderId, receiverId, amount);
        transactionPersistencePort.saveTransaction(transaction);

        return "Transferencia realizada con éxito.";
    }

    // ✅ Se agregan los métodos para TransactionController
    public Transaction createTransaction(UUID senderId, UUID receiverId, BigDecimal amount) {
        Transaction transaction = new Transaction(senderId, receiverId, amount);
        return transactionPersistencePort.saveTransaction(transaction);
    }

    public Optional<Transaction> getTransactionById(UUID id) {
        // ✅ Se envuelve en Optional para evitar errores de conversión
        return Optional.ofNullable(transactionPersistencePort.getTransactionById(id));
    }
}

