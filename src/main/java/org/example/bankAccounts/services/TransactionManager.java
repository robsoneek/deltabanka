package org.example.bankAccounts.services;

import org.example.bankAccounts.transactions.Transaction;
import org.example.bankAccounts.transactions.TransactionType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionManager {
    private final List<Transaction> allTransactions = new ArrayList<>();

    public void addTransaction(Transaction transaction) {
        allTransactions.add(transaction);
        System.out.println("Transakce ulozena: " + transaction.getTransactionId());
    }

    public List<Transaction> getAllTransactions() {
        return new ArrayList<>(allTransactions);
    }

    public List<Transaction> getTransactionsByAccount(String accountNumber) {
        return allTransactions.stream()
                .filter(t -> accountNumber.equals(t.getRecipientAccountNumber()) ||
                        accountNumber.equals(t.getSenderAccountNumber()))
                .collect(Collectors.toList());
    }

    public List<Transaction> getTransactionsByType(TransactionType type) {
        return allTransactions.stream()
                .filter(t -> t.getTransactionType() == type)
                .collect(Collectors.toList());
    }


    public List<Transaction> getTransactionsSince(LocalDateTime since) {
        return allTransactions.stream()
                .filter(t -> t.getTransactionDate().isAfter(since) ||
                        t.getTransactionDate().isEqual(since))
                .collect(Collectors.toList());
    }


    public List<Transaction> getTransactionsBetween(LocalDateTime from, LocalDateTime to) {
        return allTransactions.stream()
                .filter(t -> (t.getTransactionDate().isAfter(from) || t.getTransactionDate().isEqual(from)) &&
                        (t.getTransactionDate().isBefore(to) || t.getTransactionDate().isEqual(to)))
                .collect(Collectors.toList());
    }

    public int getTransactionCount() {
        return allTransactions.size();
    }

    public void clearAllTransactions() {
        allTransactions.clear();
        System.out.println("Vsechny transakce vymazany.");
    }


    public void displayStatistics() {
        System.out.println("=== STATISTIKA TRANSAKCI ===");
        System.out.println("Celkem transakci: " + allTransactions.size());

        for (TransactionType type : TransactionType.values()) {
            long count = allTransactions.stream()
                    .filter(t -> t.getTransactionType() == type)
                    .count();
            if (count > 0) {
                System.out.println("  " + type.getName() + ": " + count);
            }
        }
    }
}
