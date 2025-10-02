package org.example.people;

import org.example.bankAccounts.BaseBankAccount;

public class BankAccountOwner extends BaseBankAccount {
    private final String uuid;
    private final String firstName;
    private final String lastName;

    public BankAccountOwner(String uuid,  String firstName, String lastName) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUuid() {return uuid;}
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
}
