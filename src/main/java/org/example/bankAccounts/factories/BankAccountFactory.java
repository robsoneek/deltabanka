package org.example.bankAccounts.factories;

import com.google.inject.Inject;
import org.example.bankAccounts.*;
import org.example.bankAccounts.services.BankAccountNumberGenerator;
import org.example.people.Customer;

public class BankAccountFactory {

    @Inject
    private BankAccountNumberGenerator generator;

    @Inject
    private BankAccountsRegistry bankAccountsRegistry;

    public BankAccount createBankAccount(String uuid, Customer customer){
        BankAccount account = new BankAccount(
                uuid,
                generator.generateAccountNumber(),
                customer
        );
        bankAccountsRegistry.addBaseBankAccount(account);
        return account;
    }

    public StudentBankAccount createStudentBankAccount(String uuid, Customer customer, String schoolName) {
        StudentBankAccount account = new StudentBankAccount(
                uuid,
                generator.generateAccountNumber(),
                customer,
                schoolName
        );
        bankAccountsRegistry.addStudentBankAccount(account);
        return account;
    }

    public SavingBankAccount createSavingAccount(String uuid, Customer customer, float interestRate) {
        SavingBankAccount account = new SavingBankAccount(
                uuid,
                generator.generateAccountNumber(),
                customer,
                interestRate
        );
        bankAccountsRegistry.addSavingBankAccount(account);
        System.out.println("Spořící účet vytvořen: " + account.getAccountNumber() +
                ", Úroková sazba: " + interestRate + "%");
        return account;
    }

    public BankAccountWithPaymentCards createBankAccountWithPaymentCards(String uuid, Customer customer, double balance) {
        BankAccountWithPaymentCards account = new BankAccountWithPaymentCards(
                uuid,
                generator.generateAccountNumber(),
                customer,
                balance
        );
        bankAccountsRegistry.addBankAccountWithPaymentCards(account);
        return account;
    }
}