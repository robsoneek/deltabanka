package org.example;

import org.example.bankAccounts.factories.BankAccountFactory;
import org.example.bankAccounts.services.BankAccountFundsService;
import org.example.bankAccounts.services.BankAccountNumberGenerator;
import org.example.bankAccounts.BaseBankAccount;
import org.example.bankAccounts.StudentBankAccount;
import org.example.people.BankAccountOwner;
import org.example.people.BaseBankAccountOwner;
import org.example.people.Customer;
import org.example.people.factories.CustomersFactory;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        try{
            BankAccountNumberGenerator generator = new BankAccountNumberGenerator();
            BankAccountFundsService fundsService = new BankAccountFundsService();
            CustomersFactory customersFactory = new CustomersFactory();
            Customer owner = customersFactory.customer("0-123", "jouhnas", "tompest");
            BankAccountFactory  accountFactory = new BankAccountFactory();
            BaseBankAccount studentAccount = accountFactory.createStudentBankAccount("A-123", generator.generateAccountNumber(), owner, 0, "Delta");

            if (owner instanceof Customer) {
                System.out.println("Uuid: " + owner.getUuid());
                System.out.println("Name: " + owner.getFullName());
                System.out.println("School: " + ((StudentBankAccount)studentAccount).getSchoolName());
                System.out.println((""));
                System.out.println("Uuid: " + studentAccount.getUuid());
                System.out.println("Account Number: " + studentAccount.getAccountNumber());
                System.out.println("Balance: " + studentAccount.getBalance());
                fundsService.deposit(studentAccount, 15000);
                System.out.println("Balance: " + studentAccount.getBalance());
                fundsService.withdraw(studentAccount, 2500);
                System.out.println("Balance: " + studentAccount.getBalance());

            }

        }catch(Exception e){
            System.out.println(e.getMessage());
        }


    }
}