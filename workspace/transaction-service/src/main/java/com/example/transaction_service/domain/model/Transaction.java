package com.example.transaction_service.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {
    private UUID id;
    private UUID senderId;
    private UUID receiverId;
    private BigDecimal amount;
    private LocalDateTime timestamp;
    private String status;

    // 🔹 Constructor sin parámetros necesario para la serialización/deserialización
    public Transaction() {
    }

    public Transaction(UUID id, UUID senderId, UUID receiverId, BigDecimal amount, LocalDateTime timestamp, String status) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amount = amount;
        this.timestamp = timestamp;
        this.status = status;
    }

    public Transaction(UUID senderId, UUID receiverId, BigDecimal amount) {
        this(UUID.randomUUID(), senderId, receiverId, amount, LocalDateTime.now(), "PENDING");
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getSenderId() { return senderId; }
    public void setSenderId(UUID senderId) { this.senderId = senderId; }

    public UUID getReceiverId() { return receiverId; }
    public void setReceiverId(UUID receiverId) { this.receiverId = receiverId; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}



