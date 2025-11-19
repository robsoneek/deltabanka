package org.example.bankAccounts.services;

import com.google.inject.Inject;
import org.example.bankAccounts.BaseBankAccount;
import org.example.logger.TransactionLogger;
import org.example.bankAccounts.transactions.Transaction;
import org.example.bankAccounts.factories.TransactionFactory;

import java.util.Scanner;

public class BankAccountFundsService {

    @Inject
    private TransactionLogger logger;

    @Inject
    private TransactionFactory transactionFactory;

    @Inject
    private TransactionManager transactionManager;

    public void deposit(BaseBankAccount account, double amount) {
        if (amount > 10000){
            Scanner input = new Scanner(System.in);
            System.out.println("Amount to be deposited is greater than 10000" +
                    "\nAre you sure you want to continue? (y/n)");
            String answer = input.nextLine();
            if (answer.equalsIgnoreCase("y")) {
                account.setBalance(account.getBalance() + amount);

                Transaction transaction = transactionFactory.createDeposit(
                        account.getAccountNumber(),
                        amount
                );
                transactionManager.addTransaction(transaction);

                logger.logDeposit(account.getAccountNumber(), amount);
            } else {
                System.out.println("Deposit cancelled.");
                return;
            }
        } else {
            account.setBalance(account.getBalance() + amount);

            Transaction transaction = transactionFactory.createDeposit(
                    account.getAccountNumber(),
                    amount
            );
            transactionManager.addTransaction(transaction);

            logger.logDeposit(account.getAccountNumber(), amount);
        }
    }

    public void withdraw(BaseBankAccount account, double amount) {
        double subtract = account.getBalance() - amount;
        if (subtract < 0) {
            throw new IllegalArgumentException("Insufficient funds.");
        }

        account.setBalance(subtract);

        Transaction transaction = transactionFactory.createWithdrawal(
                account.getAccountNumber(),
                amount
        );
        transactionManager.addTransaction(transaction);

        logger.logWithdrawal(account.getAccountNumber(), amount);
    }
}