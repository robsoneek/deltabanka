package org.example.bankAccounts.services;

import org.example.bankAccounts.BaseBankAccount;
import org.example.people.BaseBankAccountOwner;

public class BankAccountFundsService {

    public void deposit(BaseBankAccount account, double amount) {
        if (amount > 10000){

        }
        account.setBalance(account.getBalance()+amount);
    }

    public void withdraw(BaseBankAccount account, double amount) {
        double subtract = account.getBalance() - amount;
        if (subtract < 0) {
            throw new IllegalArgumentException("Insufficient funds.");
        }

        account.setBalance(subtract);
    }
}
