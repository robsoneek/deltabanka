package org.example.bankAccounts;

import org.example.people.BaseBankAccountOwner;

public class BankAccount extends BaseBankAccount {

    public BankAccount(String uuid, String accountNumber, BaseBankAccountOwner owner, double balance) {
        super(uuid,accountNumber,owner,balance);
    }
}
