package com.rajpandith.Ewallet.dto;

import java.sql.Timestamp;

public class TransactionDto {

    private Double amount;
    private Long senderWalletId;
    private Long receiverWalletId;
    private String message;
    private Timestamp transDate; // <-- changed from LocalDateTime

    public TransactionDto(Double amount, Long senderWalletId, Long receiverWalletId, String message,
            Timestamp transDate) {
        this.amount = amount;
        this.senderWalletId = senderWalletId;
        this.receiverWalletId = receiverWalletId;
        this.message = message;
        this.transDate = transDate;
    }

    // Getters & Setters
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getSenderWalletId() {
        return senderWalletId;
    }

    public void setSenderWalletId(Long senderWalletId) {
        this.senderWalletId = senderWalletId;
    }

    public Long getReceiverWalletId() {
        return receiverWalletId;
    }

    public void setReceiverWalletId(Long receiverWalletId) {
        this.receiverWalletId = receiverWalletId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getTransDate() {
        return transDate;
    }

    public void setTransDate(Timestamp transDate) {
        this.transDate = transDate;
    }
}