package com.example.transaction_service.infrastructure.output.persistence.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transactions")
public class TransactionEntity {
    @Id
    @GeneratedValue
    private UUID id;
    private UUID senderId;
    private UUID receiverId;
    private BigDecimal amount;
    private LocalDateTime timestamp;
    private String status;

    public TransactionEntity() {}

    public TransactionEntity(UUID id, UUID senderId, UUID receiverId, BigDecimal amount, LocalDateTime timestamp, String status) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amount = amount;
        this.timestamp = timestamp;
        this.status = status;
    }

    // Getters
    public UUID getId() { return id; }
    public UUID getSenderId() { return senderId; }
    public UUID getReceiverId() { return receiverId; }
    public BigDecimal getAmount() { return amount; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getStatus() { return status; }

    // **Setters agregados**
    public void setId(UUID id) { this.id = id; }
    public void setSenderId(UUID senderId) { this.senderId = senderId; }
    public void setReceiverId(UUID receiverId) { this.receiverId = receiverId; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    public void setStatus(String status) { this.status = status; }
}




