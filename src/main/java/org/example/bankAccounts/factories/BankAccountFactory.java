package org.example.bankAccounts.factories;

import org.example.bankAccounts.BankAccount;
import org.example.bankAccounts.BaseBankAccount;
import org.example.bankAccounts.SavingBankAccount;
import org.example.bankAccounts.StudentBankAccount;
import org.example.bankAccounts.services.BankAccountNumberGenerator;
import org.example.people.BaseBankAccountOwner;
import org.example.people.Customer;

public class BankAccountFactory {

    private BankAccountNumberGenerator generator = new BankAccountNumberGenerator();

    public BankAccount createBankAccount(String uuid, String accountNumber, Customer customer, double balance){
        return  new BankAccount(
                uuid,
                generator.generateAccountNumber(),
                customer,
                balance
        );
    }

    public StudentBankAccount createStudentBankAccount(String uuid, String accountNumber, Customer customer, double balance, String schoolName) {
        return new StudentBankAccount(
                uuid,
                generator.generateAccountNumber(),
                customer,
                balance,
                schoolName
        );
    }

    public SavingBankAccount createSavingAccount(String uuid, String accountNumber, Customer customer, double balance) {
        return new SavingBankAccount(
          uuid,
          generator.generateAccountNumber(),
          customer,
          balance
        );
    }
}
