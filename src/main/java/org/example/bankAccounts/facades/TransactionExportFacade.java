package org.example.bankAccounts.facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;
import org.example.bankAccounts.factories.TransactionDataFactory;
import org.example.bankAccounts.services.TransactionManager;
import org.example.bankAccounts.transactions.Transaction;
import org.example.bankAccounts.transactions.TransactionData;
import org.example.bankAccounts.transactions.TransactionHistoryData;
import org.example.bankAccounts.transactions.TransactionType;

import java.io.IOException;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionExportFacade {
    @Inject
    private TransactionManager transactionManager;

    @Inject
    private TransactionDataFactory transactionDataFactory;

    @Inject
    private Gson gson;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final String EXPORT_DIRECTORY = "exports/";

    public String exportAllTransactions() {
        List<Transaction> transactions = transactionManager.getAllTransactions();
        return exportTransactions(transactions, "all", "Vse");
    }

    public String exportTransactionsSince(LocalDateTime since) {
        List<Transaction> transactions = transactionManager.getTransactionsSince(since);
        String filter = "since_" + since.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return exportTransactions(transactions, filter, "Od " + since.format(FORMATTER));
    }

    public String exportTransactionsByType(TransactionType type) {
        List<Transaction> transactions = transactionManager.getTransactionsByType(type);
        return exportTransactions(transactions, type.name().toLowerCase(), "Typ: " + type.getName());
    }

    public String exportTransactionsByAccount(String accountNumber) {
        List<Transaction> transactions = transactionManager.getTransactionsByAccount(accountNumber);
        return exportTransactions(transactions, "account_" + accountNumber, "Ucet: " + accountNumber);
    }

    public String exportInterestTransactions() {
        return exportTransactionsByType(TransactionType.INTEREST);
    }

    private String exportTransactions(List<Transaction> transactions, String filterName, String filterDescription) {
        System.out.println("=== Export transakci ===");
        System.out.println("Pocet transakci: " + transactions.size());
        System.out.println("Filtr: " + filterDescription);

        if (transactions.isEmpty()) {
            System.out.println("Zadne transakce k exportu.");
            return null;
        }

        List<TransactionData> transactionDataList = transactions.stream()
                .map(transactionDataFactory::createTransactionData)
                .collect(Collectors.toList());

        TransactionHistoryData historyData = new TransactionHistoryData();
        historyData.exportDate = LocalDateTime.now().format(FORMATTER);
        historyData.totalTransactions = transactions.size();
        historyData.transactions = transactionDataList;
        historyData.filterApplied = filterDescription;

        Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        String json = prettyGson.toJson(historyData);

        String filename = generateFilename(filterName);
        String filepath = EXPORT_DIRECTORY + filename;

        try {
            java.nio.file.Files.createDirectories(java.nio.file.Paths.get(EXPORT_DIRECTORY));

            FileWriter writer = new FileWriter(filepath);
            writer.write(json);
            writer.close();

            System.out.println("Export ulozen: " + filepath);
            System.out.println("Velikost: " + json.length() + " znaku");
            return filepath;

        } catch (IOException e) {
            System.err.println("CHYBA pri ukladani souboru: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private String generateFilename(String filterName) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        return String.format("transactions_%s_%s.json", filterName, timestamp);
    }

    public String getExportStatistics() {
        int total = transactionManager.getTransactionCount();

        StringBuilder stats = new StringBuilder();
        stats.append("=== STATISTIKA PRO EXPORT ===\n");
        stats.append("Celkem transakci v systemu: ").append(total).append("\n");

        for (TransactionType type : TransactionType.values()) {
            long count = transactionManager.getTransactionsByType(type).size();
            if (count > 0) {
                stats.append("  ").append(type.getName()).append(": ").append(count).append("\n");
            }
        }

        return stats.toString();
    }
}
