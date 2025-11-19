package org.example.bankAccounts.factories;

import org.example.bankAccounts.BankAccount;
import org.example.bankAccounts.transactions.Transaction;
import org.example.bankAccounts.transactions.TransactionType;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public class TransactionFactory {
    public Transaction createTransaction(String source, String target, TransactionType type, double amount) {
        String id = UUID.randomUUID().toString();
        return new Transaction(id, LocalDateTime.now(), source, target, type, amount);
    }
}
