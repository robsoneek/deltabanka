package org.example.bankAccounts;

import org.example.cards.PaymentCard;
import org.example.people.Customer;


import java.util.ArrayList;
import java.util.List;

public class BankAccountWithPaymentCards extends BaseBankAccount{
    private List<PaymentCard> paymentCards;

    public BankAccountWithPaymentCards(String uuid, String bankAccountNumber, Customer customer, double balance){
        super(uuid, bankAccountNumber, customer, balance);

        this.paymentCards = new ArrayList<>();
    }

    public void addPaymentCard(PaymentCard paymentCard){
        this.paymentCards.add(paymentCard);
        paymentCard.linkToBankAccount(this);
    }

    public void removePaymentCard(PaymentCard paymentCard) {
        this.paymentCards.remove(paymentCard);
        paymentCard.linkToBankAccount(null);
    }

    public List<PaymentCard> getPaymentCards(){
        return new ArrayList<>(this.paymentCards);
    }

}
