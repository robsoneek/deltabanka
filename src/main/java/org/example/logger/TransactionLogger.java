package org.example.logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionLogger {

    private static final String LOG_FILE = "transactions.log";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void logDeposit(String accountNumber, double amount) {
        String logMessage = String.format("[%s] DEPOSIT - Account: %s, Amount: %.2f Kč",
                getCurrentTimestamp(),
                accountNumber,
                amount);
        writeToLog(logMessage);
    }

    public void logWithdrawal(String accountNumber, double amount) {
        String logMessage = String.format("[%s] WITHDRAWAL - Account: %s, Amount: %.2f Kč",
                getCurrentTimestamp(),
                accountNumber,
                amount);
        writeToLog(logMessage);
    }

    public void logCardPayment(String cardNumber, String accountNumber, double amount) {
        String maskedCardNumber = "****" + cardNumber.substring(cardNumber.length() - 4);
        String logMessage = String.format("[%s] CARD_PAYMENT - Card: %s, Account: %s, Amount: %.2f Kč",
                getCurrentTimestamp(),
                maskedCardNumber,
                accountNumber,
                amount);
        writeToLog(logMessage);
    }


    private String getCurrentTimestamp() {
        return LocalDateTime.now().format(DATE_FORMATTER);
    }

    private void writeToLog(String message) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            writer.println(message);
            System.out.println("[LOG] " + message);
        } catch (IOException e) {
            System.err.println("Failed to write to log file: " + e.getMessage());
        }
    }

    public void displayAllLogs() {
        System.out.println("\n=== Transaction Log ===");
        try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(LOG_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Failed to read log file: " + e.getMessage());
        }
        System.out.println("=== End of Log ===\n");
    }

    public void clearLogs() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, false))) {
            writer.print("");
            System.out.println("Transaction log cleared.");
        } catch (IOException e) {
            System.err.println("Failed to clear log file: " + e.getMessage());
        }
    }
}