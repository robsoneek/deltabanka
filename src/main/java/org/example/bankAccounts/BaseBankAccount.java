package org.example.bankAccounts;

import org.example.people.Customer;

public class BaseBankAccount {

    private String uuid;

    private String accountNumber;

    private Customer customer;

    double balance;

    public BaseBankAccount(String uuid, String accountNumber, Customer customer, double balance) {
        this.uuid = uuid;
        this.accountNumber = accountNumber;
        this.customer = customer;
        this.balance = balance;
    }

    public BaseBankAccount() {
    }

    public String getUuid() {
        return uuid;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }



}
