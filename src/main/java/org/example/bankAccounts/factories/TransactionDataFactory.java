package org.example.bankAccounts.factories;

import org.example.bankAccounts.transactions.Transaction;
import org.example.bankAccounts.transactions.TransactionData;

import java.time.format.DateTimeFormatter;

public class TransactionDataFactory {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public TransactionData createTransactionData(Transaction transaction) {
        TransactionData data = new TransactionData();

        data.transactionId = transaction.getTransactionId();
        data.transactionDate = transaction.getTransactionDate().format(FORMATTER);
        data.recipientAccountNumber = transaction.getRecipientAccountNumber();
        data.senderAccountNumber = transaction.getSenderAccountNumber();
        data.transactionType = transaction.getTransactionType().name();
        data.amount = transaction.getAmount();
        data.description = transaction.getDescription();

        return data;
    }
}
