package org.example.bankAccounts.services;

import com.google.inject.Inject;
import org.example.bankAccounts.SavingBankAccount;
import org.example.logger.TransactionLogger;


public class InterestCalculatorService {

    @Inject
    private TransactionLogger logger;

    public double calculateInterest(SavingBankAccount account) {
        double currentBalance = account.getBalance();
        float interestRate = account.getInterestRate();

        double interest = currentBalance * (interestRate / 100.0);

        return interest;
    }
    public void applyInterest(SavingBankAccount account, double interest) {
        if (interest <= 0) {
            logger.logInterestCalculation(
                    account.getAccountNumber(),
                    0.0,
                    account.getBalance()
            );
            return;
        }

        double oldBalance = account.getBalance();
        double newBalance = oldBalance + interest;

        account.setBalance(newBalance);

        logger.logInterestCalculation(
                account.getAccountNumber(),
                interest,
                newBalance
        );

        System.out.println(String.format(
                "Urok pricten: Ucet %s, Puvodni zustatek: %.2f Kc, Urok: %.2f Kc (%.2f%%), Novy zustatek: %.2f Kc",
                account.getAccountNumber(),
                oldBalance,
                interest,
                account.getInterestRate(),
                newBalance
        ));
    }

    public double calculateAndApplyInterest(SavingBankAccount account) {
        double interest = calculateInterest(account);
        applyInterest(account, interest);
        return interest;
    }
}