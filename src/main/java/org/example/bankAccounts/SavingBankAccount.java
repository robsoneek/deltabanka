package org.example.bankAccounts;

import org.example.people.Customer;

public class SavingBankAccount extends BaseBankAccount {

    private final float interestRate;

    public SavingBankAccount(String uuid, String bankAccountNumber, Customer customer, float interestRate) {
        super(uuid, bankAccountNumber, customer, 0);

        this.interestRate = interestRate;
    }

    public SavingBankAccount(String uuid, String bankAccountNumber, Customer customer) {
        this(uuid, bankAccountNumber, customer, 0);
    }

    public float getInterestRate() {
        return interestRate;
    }
}