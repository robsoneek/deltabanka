package org.example.bankAccounts.transactions;

import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {

    private final String transactionId;
    private final LocalDateTime transactionDate;
    private final String recipientAccountNumber;
    private final String senderAccountNumber;
    private final TransactionType transactionType;
    private final double amount;
    private final String description;

    public Transaction(String transactionId,
                       LocalDateTime transactionDate,
                       String recipientAccountNumber,
                       String senderAccountNumber,
                       TransactionType transactionType,
                       double amount,
                       String description) {
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.recipientAccountNumber = recipientAccountNumber;
        this.senderAccountNumber = senderAccountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
        this.description = description;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public String getRecipientAccountNumber() {
        return recipientAccountNumber;
    }

    public String getSenderAccountNumber() {
        return senderAccountNumber;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s: %.2f Kc, Ucet: %s, ID: %s",
                transactionDate,
                transactionType,
                amount,
                recipientAccountNumber != null ? recipientAccountNumber : senderAccountNumber,
                transactionId);
    }
}