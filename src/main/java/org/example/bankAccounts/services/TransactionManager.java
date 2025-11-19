package org.example.bankAccounts.services;

import com.google.inject.Singleton;
import org.example.bankAccounts.transactions.Transaction;
import org.example.bankAccounts.transactions.TransactionType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class TransactionManager {
    private final List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return new ArrayList<>(transactions);
    }
}
