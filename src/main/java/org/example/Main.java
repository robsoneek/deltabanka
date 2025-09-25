package org.example;

import org.example.bankAccounts.BaseBankAccount;
import org.example.bankAccounts.StudentBankAccount;
import org.example.people.BaseBankAccountOwner;
import org.example.people.Student;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        try{
            BaseBankAccountOwner owner = new Student("O-123", "jouhnas", "tompest");
            BaseBankAccount studentAccount = new StudentBankAccount("A-123", "123", owner, 0, "Delta" );

            if (owner instanceof Student) {
                System.out.println("Uuid: " + owner.getUuid());
                System.out.println("Name: " + owner.getFullName());
                System.out.println("School: " + ((StudentBankAccount)studentAccount).getSchoolName());
                System.out.println((""));
                System.out.println("Uuid: " + studentAccount.getUuid());
                System.out.println("Account Number: " + studentAccount.getAccountNumber());
                System.out.println("Balance: " + studentAccount.getBalance());

            }

        }catch(Exception e){
            System.out.println(e.getMessage());
        }


    }
}