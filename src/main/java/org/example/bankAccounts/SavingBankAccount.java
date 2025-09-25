package org.example.bankAccounts;

import org.example.people.BaseBankAccountOwner;

public class SavingBankAccount extends BaseBankAccount {

    public SavingBankAccount(String uuid, String accountNumber, BaseBankAccountOwner owner, double balance) {
        super(uuid, accountNumber,owner,balance);


    }
}
