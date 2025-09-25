package org.example.bankAccounts;

import org.example.people.BaseBankAccountOwner;

public class StudentBankAccount extends BaseBankAccount{
    private String schoolName;
    public StudentBankAccount(String uuid, String accountNumber, BaseBankAccountOwner owner, double balance, String schoolName) {
        super(uuid, accountNumber, owner, balance);
        this.schoolName = schoolName;
    }

    public String getSchoolName() {
        return schoolName;
    }
}
