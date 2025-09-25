package org.example.people;

import org.example.bankAccounts.BaseBankAccount;

public class BankAccountOwner extends BaseBankAccount {

    public BankAccountOwner(String uuid, String accountNumber, BaseBankAccountOwner owner, double balance) {
        super(uuid, accountNumber, owner, balance);
    }
}
