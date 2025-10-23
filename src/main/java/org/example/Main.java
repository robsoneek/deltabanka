package org.example;

import org.example.bankAccounts.BankAccountWithPaymentCards;
import org.example.bankAccounts.factories.BankAccountFactory;
import org.example.bankAccounts.services.BankAccountFundsService;
import org.example.bankAccounts.services.BankAccountNumberGenerator;
import org.example.bankAccounts.BaseBankAccount;
import org.example.bankAccounts.StudentBankAccount;
import org.example.cards.PaymentCard;
import org.example.cards.PaymentCardFactory;
import org.example.cards.services.PaymentCardService;
import org.example.people.Customer;
import org.example.people.factories.CustomersFactory;
import org.example.serialization.BankAccountOwnerJsonSerializationServiceXML;
import org.example.serialization.Serialization;
import org.example.logger.TransactionLogger;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {


    public static void main(String[] args) {

        try{
            TransactionLogger logger = new TransactionLogger();
            PaymentCardService paymentCardService = new PaymentCardService();
            BankAccountFactory bankAccountFactory = new BankAccountFactory();
            BankAccountNumberGenerator generator = new BankAccountNumberGenerator();
            BankAccountFundsService fundsService = new BankAccountFundsService();
            Serialization serializationService = new BankAccountOwnerJsonSerializationServiceXML();
            CustomersFactory customersFactory = new CustomersFactory();
            Customer owner = customersFactory.customer("0-123", "jouhnas", "tompest");
            BaseBankAccount bankAccount = bankAccountFactory.createBankAccount("123", owner);
            StudentBankAccount studentBankAccount = bankAccountFactory.createStudentBankAccount("123", owner, "Delta");
            BankAccountWithPaymentCards bankAccountWithCard = bankAccountFactory.createBankAccountWithPaymentCards("123", owner,
                    5000);
            PaymentCardFactory cardFactory = new PaymentCardFactory();
            PaymentCard card = cardFactory.create();
            bankAccountWithCard.addPaymentCard(card);

            if (owner instanceof Customer) {
                System.out.println("Uuid: " + owner.getUuid());
                System.out.println("Name: " + owner.getFullName());
                System.out.println("School: " + (studentBankAccount).getSchoolName());
                System.out.println((""));
                System.out.println("Uuid: " + bankAccount.getUuid());
                System.out.println("Account Number: " + bankAccount.getAccountNumber());
                System.out.println("Balance: " + bankAccount.getBalance());
                fundsService.deposit(bankAccount, 15000);
                System.out.println("Balance: " + bankAccount.getBalance());
                fundsService.withdraw(bankAccount, 2500);
                System.out.println("Balance: " + bankAccount.getBalance());
                System.out.println("=== Card Info ===");
                paymentCardService.displayCardInfo(card);
                System.out.println("PIN: " + card.getPin());
                System.out.println("CVV: " + card.getCvv());
                System.out.println();
                System.out.println("=== Payment ===");
                paymentCardService.makePayment(card, bankAccountWithCard, 1500.0, card.getPin());
                System.out.println();
                System.out.println("Balance: " + bankAccount.getBalance());
                System.out.print(serializationService.serialize(owner));

                System.out.println("\n");
                logger.displayAllLogs();


            }

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}