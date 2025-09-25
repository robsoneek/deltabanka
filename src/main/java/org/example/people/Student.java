package org.example.people;

import org.example.bankAccounts.BaseBankAccount;

public class Student extends BaseBankAccountOwner {

    public Student(String uuid, String firstName, String lastName) {
        super(uuid, firstName, lastName);
    }

}
