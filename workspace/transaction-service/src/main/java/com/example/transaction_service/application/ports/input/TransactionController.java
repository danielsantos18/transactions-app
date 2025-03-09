package com.example.transaction_service.application.ports.input;

import com.example.transaction_service.application.service.TransactionService;
import com.example.transaction_service.domain.model.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // Endpoint para crear una transacción
    @PostMapping
    public ResponseEntity<Transaction> createTransaction(
            @RequestParam String senderUsername,
            @RequestParam String receiverUsername,
            @RequestParam BigDecimal amount) {

        Transaction transaction = transactionService.createTransaction(senderUsername, receiverUsername, amount);
        return ResponseEntity.ok(transaction);
    }

    // Endpoint para obtener una transacción por ID
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransaction(@PathVariable UUID id) {
        Optional<Transaction> transaction = transactionService.getTransactionById(id);
        return transaction.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para listar todas las transacciones
    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }
}