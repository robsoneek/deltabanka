package org.example.bankAccounts.transactions; // Zkontroluj, že package sedí

import java.time.LocalDateTime;

public class Transaction {

    private String transactionId;
    private LocalDateTime transactionDate;
    private String senderAccountNumber;
    private String recipientAccountNumber;
    private TransactionType transactionType;
    private double amount;
    private String description;

    public Transaction(String transactionId, LocalDateTime transactionDate,
                       String senderAccountNumber, String recipientAccountNumber,
                       TransactionType transactionType, double amount, String description) {
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.senderAccountNumber = senderAccountNumber;
        this.recipientAccountNumber = recipientAccountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
        this.description = description;
    }

    public Transaction(String transactionId, LocalDateTime transactionDate,
                       String senderAccountNumber, String recipientAccountNumber,
                       TransactionType transactionType, double amount) {
        this(transactionId, transactionDate, senderAccountNumber, recipientAccountNumber, transactionType, amount, "");
    }


    public String getTransactionId() {
        return transactionId;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public String getSenderAccountNumber() {
        return senderAccountNumber;
    }

    public String getRecipientAccountNumber() {
        return recipientAccountNumber;
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
}