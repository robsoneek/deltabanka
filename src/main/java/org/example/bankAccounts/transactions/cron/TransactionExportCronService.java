package org.example.bankAccounts.transactions.cron;

import com.google.inject.Inject;
import org.example.bankAccounts.facades.TransactionExportFacade;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public class TransactionExportCronService {
    @Inject
    private TransactionExportFacade facade;

    private Timer timer;

    public void start() {
        timer = new Timer("TransactionExportTimer", true);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("--- CRON EXPORT SPUŠTĚN ---");
                facade.exportTransactions();
            }
        };

        timer.scheduleAtFixedRate(task, 5000, 60 * 1000);
        System.out.println("Transaction Export Cron Service STARTED");
    }

    public void stop() {
        if (timer != null) {
            timer.cancel();
        }
    }
}
