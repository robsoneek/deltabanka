package org.example;

import com.google.inject.Inject;
import org.example.bankAccounts.BankAccountWithPaymentCards;
import org.example.bankAccounts.BaseBankAccount;
import org.example.bankAccounts.SavingBankAccount;
import org.example.bankAccounts.StudentBankAccount;
import org.example.bankAccounts.facades.InterestManagementFacade;
import org.example.bankAccounts.factories.BankAccountFactory;
import org.example.bankAccounts.services.BankAccountFundsService;
import org.example.bankAccounts.transactions.cron.TransactionExportCronService;
import org.example.cards.PaymentCard;
import org.example.cards.PaymentCardFactory;
import org.example.cards.services.PaymentCardService;
import org.example.cron.InterestCalculationCronService;
import org.example.logger.TransactionLogger;
import org.example.people.Customer;
import org.example.people.factories.CustomersFactory;
import org.example.serialization.Serialization;

public class App {

    @Inject
    private CustomersFactory customersFactory;

    @Inject
    private TransactionLogger transactionLogger;

    @Inject
    private BankAccountFactory bankAccountFactory;

    @Inject
    private PaymentCardFactory paymentCardFactory;

    @Inject
    private BankAccountFundsService bankAccountFundsService;

    @Inject
    private PaymentCardService paymentCardService;

    @Inject
    private Serialization serialization;

    @Inject
    private InterestManagementFacade interestManagementFacade;

    @Inject
    private InterestCalculationCronService cronService;

    @Inject
    private TransactionExportCronService  transactionExportCronService;

    public void run() {
        try {
            transactionLogger.clearLogs();

            Customer owner = customersFactory.customer("0-123", "Jouhnas", "Tompest");
            BaseBankAccount bankAccount = bankAccountFactory.createBankAccount("123", owner);
            StudentBankAccount studentBankAccount = bankAccountFactory.createStudentBankAccount(
                    "124", owner, "Delta"
            );

            System.out.println("\n=== Vytvareni sporicich uctu ===");
            SavingBankAccount savingAccount1 = bankAccountFactory.createSavingAccount(
                    "125", owner, 2.5f
            );
            SavingBankAccount savingAccount2 = bankAccountFactory.createSavingAccount(
                    "126", owner, 3.0f
            );
            SavingBankAccount savingAccount3 = bankAccountFactory.createSavingAccount(
                    "127", owner, 1.5f
            );

            System.out.println("\n=== Vkladani prostredku ===");
            bankAccountFundsService.deposit(bankAccount, 15000);
            bankAccountFundsService.deposit(savingAccount1, 10000);
            bankAccountFundsService.deposit(savingAccount2, 50000);
            bankAccountFundsService.deposit(savingAccount3, 25000);

            System.out.println("\n=== Informace o uctech ===");
            System.out.println("Zakaznik: " + owner.getFullName());
            System.out.println("UUID: " + owner.getUuid());
            System.out.println();
            System.out.println("Bezny ucet: " + bankAccount.getAccountNumber());
            System.out.println("  Zustatek: " + bankAccount.getBalance() + " Kc");
            System.out.println();
            System.out.println("Sporici ucet 1: " + savingAccount1.getAccountNumber());
            System.out.println("  Zustatek: " + savingAccount1.getBalance() + " Kc");
            System.out.println("  Urokova sazba: " + savingAccount1.getInterestRate() + "%");
            System.out.println();
            System.out.println("Sporici ucet 2: " + savingAccount2.getAccountNumber());
            System.out.println("  Zustatek: " + savingAccount2.getBalance() + " Kc");
            System.out.println("  Urokova sazba: " + savingAccount2.getInterestRate() + "%");
            System.out.println();
            System.out.println("Sporici ucet 3: " + savingAccount3.getAccountNumber());
            System.out.println("  Zustatek: " + savingAccount3.getBalance() + " Kc");
            System.out.println("  Urokova sazba: " + savingAccount3.getInterestRate() + "%");

            BankAccountWithPaymentCards bankAccountWithCard =
                    bankAccountFactory.createBankAccountWithPaymentCards("128", owner, 5000);
            PaymentCard card = paymentCardFactory.create();
            bankAccountWithCard.addPaymentCard(card);

            System.out.println("\n=== Informace o karte ===");
            paymentCardService.displayCardInfo(card);

            System.out.println("\n=== Provedeni platby kartou ===");
            paymentCardService.makePayment(card, bankAccountWithCard, 1500.0, card.getPin());

            System.out.println("\n=== Statistiky uroceni (pred cronem) ===");
            System.out.println(interestManagementFacade.getInterestStatistics());

            System.out.println("\n");
            cronService.start();

            System.out.println("Cron service bezi... (3 minuty)");

            Thread.sleep(3 * 60 * 1000); // 3 minuty

            cronService.stop();

            System.out.println("\n=== FINALNI STAV UCTU ===");
            System.out.println("Bezny ucet: " + bankAccount.getBalance() + " Kc");
            System.out.println("Sporici ucet 1: " + savingAccount1.getBalance() + " Kc");
            System.out.println("Sporici ucet 2: " + savingAccount2.getBalance() + " Kc");
            System.out.println("Sporici ucet 3: " + savingAccount3.getBalance() + " Kc");
            System.out.println("Ucet s kartou: " + bankAccountWithCard.getBalance() + " Kc");

            System.out.println("\n");
            transactionLogger.displayAllLogs();

        } catch (Exception e) {
            System.err.println("CHYBA: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void runCronOnly() {
        try {
            System.out.println("========================================");
            System.out.println("   BANKING SYSTEM - CRON SERVICE MODE    ");
            System.out.println("========================================");
            System.out.println();

            cronService.start();

            System.out.println("OK Cron service aktivni");
            System.out.println("OK Automaticke uroceni kazdou minutu");
            System.out.println("OK Stisknete Ctrl+C pro ukonceni");
            System.out.println();

            while (cronService.isRunning()) {
                Thread.sleep(1000);
            }

        } catch (Exception e) {
            System.err.println("CHYBA: " + e.getMessage());
            e.printStackTrace();
        }
    }
}