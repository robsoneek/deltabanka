package org.example.cron;

import com.google.inject.Inject;
import org.example.bankAccounts.facades.InterestManagementFacade;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;


public class InterestCalculationCronService {
    @Inject
    private InterestManagementFacade interestManagementFacade;

    private Timer timer;
    private boolean isRunning = false;
    private int executionCount = 0;
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");


    public void start() {
        if (isRunning) {
            System.out.println("Cron service již běží!");
            return;
        }

        timer = new Timer("InterestCalculationCronTimer", true);
        isRunning = true;
        executionCount = 0;

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                executeInterestCalculation();
            }
        };

        long delay = 0;
        long period = 60 * 100;

        timer.scheduleAtFixedRate(task, delay, period);

        System.out.println("========================================");
        System.out.println("  Interest Calculation Cron Service STARTED");
        System.out.println("========================================");
        System.out.println("Interval: kazdou minutu (60s)");
        System.out.println("Cas spusteni: " + LocalDateTime.now().format(TIME_FORMATTER));
        System.out.println();
    }


    public void stop() {
        if (!isRunning) {
            System.out.println("Cron service nebezi!");
            return;
        }

        if (timer != null) {
            timer.cancel();
            timer = null;
        }

        isRunning = false;

        System.out.println();
        System.out.println("========================================");
        System.out.println("  Interest Calculation Cron Service STOPPED");
        System.out.println("========================================");
        System.out.println("Celkem spusteni: " + executionCount);
        System.out.println("Cas zastaveni: " + LocalDateTime.now().format(TIME_FORMATTER));
        System.out.println();
    }


    private void executeInterestCalculation() {
        executionCount++;

        System.out.println("------------------------------------------------");
        System.out.println("  CRON: Spusteni #" + executionCount);
        System.out.println("  Cas: " + LocalDateTime.now().format(TIME_FORMATTER));
        System.out.println("------------------------------------------------");

        try {
            int processedAccounts = interestManagementFacade.processAllSavingAccountsInterest();

            if (processedAccounts == 0) {
                System.out.println("-> Zadne ucty ke zpracovani v tomto cyklu");
            } else {
                System.out.println("OK Uspesne zpracovano " + processedAccounts + " uctu");
            }

        } catch (Exception e) {
            System.err.println("ERROR pri vypoctu uroku: " + e.getMessage());
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
        System.out.println("=== MANUALNI SPUSTENI UROCENI ===");
        executeInterestCalculation();
    }
}
