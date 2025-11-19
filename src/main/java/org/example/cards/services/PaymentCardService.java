package org.example.cards.services;

import com.google.inject.Inject;
import org.example.bankAccounts.BankAccountWithPaymentCards;
import org.example.bankAccounts.factories.TransactionFactory;
import org.example.bankAccounts.services.TransactionManager;
import org.example.bankAccounts.transactions.Transaction;
import org.example.cards.PaymentCard;
import org.example.bankAccounts.services.BankAccountFundsService;
import org.example.logger.TransactionLogger;

import java.time.DateTimeException;
import java.time.YearMonth;

public class PaymentCardService {

    @Inject
    private BankAccountFundsService fundsService;

    @Inject
    private TransactionLogger logger;

    @Inject
    private TransactionFactory transactionFactory;

    @Inject
    private TransactionManager transactionManager;

    public boolean makePayment(PaymentCard card, BankAccountWithPaymentCards bankAccount, double amount, String pin) {
        if(!card.getPin().equals(pin))
            throw new IllegalArgumentException("Invalid card pin");

        if(!bankAccount.getPaymentCards().stream()
                .anyMatch(c -> c.getCardNumber().equals(card.getCardNumber()))){
            throw new IllegalArgumentException("Invalid card number");
        }

        if (isCardExpired(card)) {
            throw new IllegalArgumentException("Card is expired");
        }

        if (bankAccount.getBalance() < amount) {
            throw new IllegalArgumentException("Invalid balance");
        }

        double oldBalance = bankAccount.getBalance();
        bankAccount.setBalance(oldBalance - amount);
        Transaction transaction = transactionFactory.createCardPayment(
                card.getCardNumber(),
                bankAccount.getAccountNumber(),
                amount
        );
        transactionManager.addTransaction(transaction);

        System.out.println(String.format("Payment successful: %.2f ", amount));
        System.out.println(String.format("Card Number: ****%s",
                card.getCardNumber().substring(card.getCardNumber().length() - 4)));
        System.out.println(String.format("Balance: %.2f Kc", bankAccount.getBalance()));

        logger.logCardPayment(card.getCardNumber(), bankAccount.getAccountNumber(), amount);

        return true;
    }

    private boolean isCardExpired(PaymentCard card) {
        try {
            int month = Integer.parseInt(card.getExpireMonth());
            int year = Integer.parseInt(card.getExpireYear());
            YearMonth expiryDate = YearMonth.of(year, month);
            YearMonth currentDate = YearMonth.now();

            if(currentDate.isAfter(expiryDate)){
                return true;
            }

        } catch (NumberFormatException | DateTimeException e) {
            return true;
        }
        return false;
    }

    public void displayCardInfo(PaymentCard card) {
        String maskedNumber = "****-****-****-" +
                card.getCardNumber().substring(card.getCardNumber().length() - 4);
        System.out.println("Card Number: " + maskedNumber);
        System.out.println("Expire date: " + card.getExpireMonth() + "/" + card.getExpireYear());
    }

    public double checkBalance(PaymentCard card, BankAccountWithPaymentCards bankAccount)
            throws IllegalAccessError {
        if (!bankAccount.getPaymentCards().stream()
                .anyMatch(c -> c.getCardNumber().equals(card.getCardNumber()))) {
            throw new IllegalArgumentException("Card is not linked to this account");
        }
        return bankAccount.getBalance();
    }

    public void displayCardInfoWithBalance(PaymentCard card, BankAccountWithPaymentCards bankAccount)
            throws IllegalAccessError {
        displayCardInfo(card);
        double balance = checkBalance(card, bankAccount);
        System.out.println(String.format("Balance: %.2f Kc", balance));
    }
}