package org.example.bankAccounts.factories;

import com.google.inject.Inject;
import org.example.bankAccounts.BankAccount;
import org.example.bankAccounts.BankAccountWithPaymentCards;
import org.example.bankAccounts.SavingBankAccount;
import org.example.bankAccounts.StudentBankAccount;
import org.example.bankAccounts.services.BankAccountNumberGenerator;
import org.example.people.Customer;

public class BankAccountFactory {

    @Inject
    private BankAccountNumberGenerator generator;

    public BankAccount createBankAccount(String uuid, Customer customer){
        return                            new BankAccount(
                uuid,
                generator.generateAccountNumber(),
                customer
        );
    }

    public StudentBankAccount createStudentBankAccount(String uuid, Customer customer, String schoolName) {
        return new StudentBankAccount(
                uuid,
                generator.generateAccountNumber(),
                customer,
                schoolName
        );
    }

    public SavingBankAccount createSavingAccount(String uuid, Customer customer, float interestRate) {
        return new SavingBankAccount(
          uuid,
          generator.generateAccountNumber(),
          customer,
                interestRate
        );
    }

    public BankAccountWithPaymentCards createBankAccountWithPaymentCards(String uuid, Customer customer, double balance) {
        return new BankAccountWithPaymentCards(
                uuid,
                generator.generateAccountNumber(),
                customer,
                balance

        );
    }
}
