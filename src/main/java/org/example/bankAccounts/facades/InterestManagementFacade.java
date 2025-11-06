package org.example.bankAccounts.facades;

import com.google.inject.Inject;
import org.example.bankAccounts.BankAccountsRegistry;
import org.example.bankAccounts.SavingBankAccount;
import org.example.bankAccounts.services.InterestCalculatorService;

import java.time.LocalDateTime;
import java.util.List;


public class InterestManagementFacade {

    @Inject
    private BankAccountsRegistry bankAccountsRegistry;

    @Inject
    private InterestCalculatorService interestCalculatorService;

    public int processAllSavingAccountsInterest() {
        List<SavingBankAccount> savingAccounts = bankAccountsRegistry.getAllSavingBankAccounts();

        if (savingAccounts.isEmpty()) {
            System.out.println("Zadne sporici ucty k uroceni.");
            return 0;
        }

        int processedCount = 0;
        LocalDateTime now = LocalDateTime.now();

        System.out.println("=== Spusteni procesu uroceni ===");
        System.out.println("Cas: " + now);
        System.out.println("Pocet sporicich uctu: " + savingAccounts.size());
        System.out.println();

        for (SavingBankAccount account : savingAccounts) {
            if (shouldCalculateInterest(account, now)) {
                processAccountInterest(account, now);
                processedCount++;
            }
        }

        System.out.println();
        System.out.println("=== Proces uroceni dokoncen ===");
        System.out.println("Uroceno uctu: " + processedCount + "/" + savingAccounts.size());
        System.out.println();

        return processedCount;
    }

    private boolean shouldCalculateInterest(SavingBankAccount account, LocalDateTime currentTime) {
        LocalDateTime nextCalculationDate = account.getNextInterestCalculationDate();

        if (nextCalculationDate == null) {
            account.setNextInterestCalculationDate(currentTime);
            return true;
        }

        return currentTime.isAfter(nextCalculationDate) ||
                currentTime.isEqual(nextCalculationDate);
    }

    private void processAccountInterest(SavingBankAccount account, LocalDateTime currentTime) {
        System.out.println("Zpracovani uctu: " + account.getAccountNumber());
        System.out.println("  Urokova sazba: " + account.getInterestRate() + "%");
        System.out.println("  Zustatek pred urocenim: " + account.getBalance() + " Kc");

        double interest = interestCalculatorService.calculateAndApplyInterest(account);

        LocalDateTime nextDate = currentTime.plusMinutes(2);
        //LocalDateTime nextDate = currentTime.plusMonths(1);
        account.setNextInterestCalculationDate(nextDate);

        System.out.println("  Pricteny urok: " + interest + " Kc");
        System.out.println("  Novy zustatek: " + account.getBalance() + " Kc");
        System.out.println("  Dalsi uroceni: " + nextDate);
        System.out.println();
    }

    public void forceInterestCalculation(SavingBankAccount account) {
        System.out.println("=== Vynucene uroceni uctu " + account.getAccountNumber() + " ===");
        processAccountInterest(account, LocalDateTime.now());
    }

    public String getInterestStatistics() {
        List<SavingBankAccount> accounts = bankAccountsRegistry.getAllSavingBankAccounts();
        LocalDateTime now = LocalDateTime.now();

        int waitingCount = 0;
        int readyCount = 0;

        for (SavingBankAccount account : accounts) {
            if (shouldCalculateInterest(account, now)) {
                readyCount++;
            } else {
                waitingCount++;
            }
        }

        return String.format(
                "Celkem sporicich uctu: %d\nPripraveno k uroceni: %d\nCeka na uroceni: %d",
                accounts.size(),
                readyCount,
                waitingCount
        );
    }
}