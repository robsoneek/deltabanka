package org.example.bankAccounts;

import org.example.people.Customer;

import java.time.LocalDateTime;

public class SavingBankAccount extends BaseBankAccount {

    private final float interestRate;
    private LocalDateTime nextInterestCalculationDate;

    public SavingBankAccount(String uuid, String bankAccountNumber, Customer customer, float interestRate) {
        super(uuid, bankAccountNumber, customer, 0);
        this.interestRate = interestRate;
        //this.nextInterestCalculationDate = LocalDateTime.now().plusMonths(1);
        this.nextInterestCalculationDate = LocalDateTime.now();
    }

    public float getInterestRate() {
        return interestRate;
    }

    public LocalDateTime getNextInterestCalculationDate() {
        return nextInterestCalculationDate;
    }

    public void setNextInterestCalculationDate(LocalDateTime nextInterestCalculationDate) {
        this.nextInterestCalculationDate = nextInterestCalculationDate;
    }
}