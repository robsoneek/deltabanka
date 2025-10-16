package org.example.cards;

import org.example.bankAccounts.BankAccount;
import org.example.bankAccounts.BankAccountWithPaymentCards;

public class PaymentCard
{
    private String uuid;
    private String cardNumber;
    private String cvv;
    private String pin;
    private String expireMonth;
    private String expireYear;
    private BankAccountWithPaymentCards linkedBankAccount;

    public PaymentCard(String uuid, String cardNumber, String cvv, String pin, String expireMonth, String expireYear) {
        this.uuid = uuid;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.pin = pin;
        this.expireMonth = expireMonth;
        this.expireYear = expireYear;
        this.linkedBankAccount = null;
    }

    public void linkToBankAccount(BankAccountWithPaymentCards bankAccountWithPaymentCards)
    {
        this.linkedBankAccount = bankAccountWithPaymentCards;
    }

    public BankAccountWithPaymentCards getLinkedBankAccount() {
        return linkedBankAccount;
    }

    public double getBalance() {
        if (linkedBankAccount == null) {
            throw new IllegalStateException("Card is not linked to a bank account");
        }
        return linkedBankAccount.getBalance();
    }

    public String getUuid() {
        return uuid;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public String getPin() {
        return pin;
    }

    public String getExpireMonth() {
        return expireMonth;
    }

    public String getExpireYear() {
        return expireYear;
    }
}
