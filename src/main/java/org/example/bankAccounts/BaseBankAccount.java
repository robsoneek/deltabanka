package org.example.bankAccounts;

import org.example.people.BaseBankAccountOwner;

public class BaseBankAccount {

    private String uuid;

    private String accountNumber;

    private BaseBankAccountOwner owner;

    double balance;

    public BaseBankAccount(String uuid, String accountNumber, BaseBankAccountOwner owner, double balance) {
        this.uuid = uuid;
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = balance;
    }
    public String getUuid() {
        return uuid;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BaseBankAccountOwner getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    public void addBalance(double balance) {
        this.balance += balance;
    }

    public void withdraw(double balance) {
        double subtract = this.balance - balance;
        if (subtract > 0) {
            throw new IllegalArgumentException("Insufficient funds.");
        }

        this.balance = subtract;
    }

}
