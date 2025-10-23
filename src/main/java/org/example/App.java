package org.example;

import com.google.inject.Guice;
import com.google.inject.Inject;
import org.example.bankAccounts.BankAccountWithPaymentCards;
import org.example.bankAccounts.BaseBankAccount;
import org.example.bankAccounts.StudentBankAccount;
import org.example.bankAccounts.factories.BankAccountFactory;
import org.example.bankAccounts.services.BankAccountFundsService;
import org.example.cards.PaymentCard;
import org.example.cards.PaymentCardFactory;
import org.example.cards.services.PaymentCardService;
import org.example.logger.TransactionLogger;
import org.example.people.Customer;
import org.example.people.factories.CustomersFactory;
import org.example.serialization.Serialization;

public class App {

    @Inject
    private CustomersFactory  customersFactory;

    @Inject
    private TransactionLogger transactionLogger;

    @Inject
    private BankAccountFactory bankAccountFactory;

    @Inject
    private PaymentCardFactory paymentCardFactory;

    @Inject
    private BankAccountFundsService bankAccountFundsService;

    @Inject
    private PaymentCardService  paymentCardService;

    @Inject
    private Serialization serialization;

    public void run() {

        try{
            transactionLogger.clearLogs();
            Customer owner = customersFactory.customer("0-123", "jouhnas", "tompest");
            BaseBankAccount bankAccount = bankAccountFactory.createBankAccount("123", owner);
            StudentBankAccount studentBankAccount = bankAccountFactory.createStudentBankAccount("123", owner,
                    "Delta");
            BankAccountWithPaymentCards bankAccountWithCard =
                    bankAccountFactory.createBankAccountWithPaymentCards("123", owner,
                            5000);
            PaymentCard card = paymentCardFactory.create();
            bankAccountWithCard.addPaymentCard(card);

            if (owner instanceof Customer) {
                System.out.println("Uuid: " + owner.getUuid());
                System.out.println("Name: " + owner.getFullName());
                System.out.println("School: " + (studentBankAccount).getSchoolName());
                System.out.println((""));
                System.out.println("Uuid: " + bankAccount.getUuid());
                System.out.println("Account Number: " + bankAccount.getAccountNumber());
                System.out.println("Balance: " + bankAccount.getBalance());
                bankAccountFundsService.deposit(bankAccount, 15000);
                System.out.println("Balance: " + bankAccount.getBalance());
                bankAccountFundsService.withdraw(bankAccount, 2500);
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
                System.out.println("=== Serialization ===");
                System.out.print(serialization.serialize(owner));

                System.out.println("\n");
                transactionLogger.displayAllLogs();


            }

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
