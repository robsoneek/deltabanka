package org.example.bankAccounts.services;

import org.example.bankAccounts.BaseBankAccount;
import org.example.people.BaseBankAccountOwner;
import java.util.Scanner;

public class BankAccountFundsService {

    public void deposit(BaseBankAccount account, double amount) {
        if (amount > 10000){
            Scanner input = new Scanner(System.in);
            System.out.println("Amount to be deposited is greater than 10000" +
                    "\nAre you sure you want to continue? (y/n)");
            String answer = input.nextLine();
            if (answer.equalsIgnoreCase("y"))
                account.setBalance(account.getBalance()+amount);
            else
                amount=0;

        }else
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
