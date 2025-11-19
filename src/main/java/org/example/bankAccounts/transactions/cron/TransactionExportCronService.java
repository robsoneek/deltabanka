package org.example.bankAccounts.transactions.cron;

import com.google.inject.Inject;
import org.example.bankAccounts.facades.TransactionExportFacade;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public class TransactionExportCronService {
    @Inject
    private TransactionExportFacade exportFacade;

    private Timer timer;
    private boolean isRunning = false;
    private int executionCount = 0;
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    public void start() {
        if (isRunning) {
            System.out.println("Transaction Export Cron service jiz bezi!");
            return;
        }

        timer = new Timer("TransactionExportCronTimer", true);
        isRunning = true;
        executionCount = 0;

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                executeExport();
            }
        };

        long delay = 60 * 1000;
        long period = 5 * 60 * 1000;

        timer.scheduleAtFixedRate(task, delay, period);
        System.out.println();
    }

    public void stop() {
        if (!isRunning) {
            System.out.println("Transaction Export Cron service nebezi!");
            return;
        }

        if (timer != null) {
            timer.cancel();
            timer = null;
        }

        isRunning = false;
    }

    private void executeExport() {
        executionCount++;

        System.out.println("  CRON: Automaticky export transakci - Export #" + executionCount);

        try {
            System.out.println(exportFacade.getExportStatistics());

            String filepath = exportFacade.exportAllTransactions();

            if (filepath != null) {
                System.out.println("OK Export uspesne dokoncen");
            } else {
                System.out.println("-> Zadne transakce k exportu");
            }

        } catch (Exception e) {
            System.err.println("ERROR pri exportu transakci: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println();
    }

    public boolean isRunning() {
        return isRunning;
    }

    public int getExecutionCount() {
        return executionCount;
    }

    public void triggerManually() {
        System.out.println("=== MANUALNI SPUSTENI EXPORTU ===");
        executeExport();
    }
}
