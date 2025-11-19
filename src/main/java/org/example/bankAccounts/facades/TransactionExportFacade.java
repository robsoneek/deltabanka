package org.example.bankAccounts.facades;

import com.google.inject.Inject;
import com.google.gson.Gson;
import org.example.bankAccounts.services.TransactionManager;
import org.example.bankAccounts.transactions.Transaction;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import java.lang.reflect.Type;
import java.time.LocalDateTime;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionExportFacade {

    @Inject
    private TransactionManager transactionManager;

    @Inject
    private Gson gson;

    public void exportTransactions() {

        List<Transaction> allTransactions = transactionManager.getAllTransactions();
        String filename = "export_transactions.json";

        List<Transaction> filtered = allTransactions.stream()
                .filter(t -> t.getAmount() > 0)
                .collect(Collectors.toList());

        if (filtered.isEmpty()) {
            System.out.println("Žádné transakce k exportu.");
            return;
        }
        try (FileWriter writer = new FileWriter(filename)) {
            String json = gson.toJson(filtered);
            writer.write(json);
            System.out.println("Exportováno " + filtered.size() + " transakcí do souboru " + filename);
        } catch (IOException e) {
            System.err.println("Chyba při zápisu exportu: " + e.getMessage());
            e.printStackTrace();
        }
    }
}