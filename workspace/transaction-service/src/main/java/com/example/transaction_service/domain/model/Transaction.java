package com.example.transaction_service.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {
    private UUID id;
    private String senderUsername; // Cambiamos a String para el nombre de usuario
    private String receiverUsername; // Cambiamos a String para el nombre de usuario
    private BigDecimal amount;
    private LocalDateTime timestamp;
    private String status;

    // Constructor sin parámetros
    public Transaction() {
    }

    // Constructor con parámetros
    public Transaction(UUID id, String senderUsername, String receiverUsername, BigDecimal amount, LocalDateTime timestamp, String status) {
        this.id = id;
        this.senderUsername = senderUsername;
        this.receiverUsername = receiverUsername;
        this.amount = amount;
        this.timestamp = timestamp;
        this.status = status;
    }

    // Constructor simplificado
    public Transaction(String senderUsername, String receiverUsername, BigDecimal amount) {
        this(UUID.randomUUID(), senderUsername, receiverUsername, amount, LocalDateTime.now(), "PENDING");
    }

    // Getters y setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getSenderUsername() { return senderUsername; }
    public void setSenderUsername(String senderUsername) { this.senderUsername = senderUsername; }

    public String getReceiverUsername() { return receiverUsername; }
    public void setReceiverUsername(String receiverUsername) { this.receiverUsername = receiverUsername; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}



