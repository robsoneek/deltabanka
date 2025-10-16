package org.example.bankAccounts;

import org.example.people.Customer;

public class StudentBankAccount extends BankAccountWithPaymentCards {
    private final String schoolName;

    public StudentBankAccount(String uuid, String accountNumber, Customer customer, String schoolName) {
        super(uuid, accountNumber, customer, 0);
        this.schoolName = schoolName;
    }

    public String getSchoolName() {
        return schoolName;
    }
}
