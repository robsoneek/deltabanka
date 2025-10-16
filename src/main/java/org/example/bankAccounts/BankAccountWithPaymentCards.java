package org.example.bankAccounts;

import org.example.cards.PaymentCard;
import org.example.people.Customer;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankAccountWithPaymentCards extends BaseBankAccount{
    private List<PaymentCard> paymentCards;
    public Map<String, PaymentCard> paymentCardsMap;

    public BankAccountWithPaymentCards(String uuid, String bankAccountNumber, Customer customer, double balance){
        super(uuid, bankAccountNumber, customer, balance);

        this.paymentCards = new ArrayList<>();
        this.paymentCardsMap = new HashMap<>();
    }

    public void addPaymentCard(PaymentCard paymentCard){
        this.paymentCards.add(paymentCard);
        this.paymentCardsMap.put(paymentCard.getCardNumber(), paymentCard);
        paymentCard.linkToBankAccount(this);
    }

    public void removePaymentCard(PaymentCard paymentCard) {
        this.paymentCards.remove(paymentCard);
        this.paymentCardsMap.remove(paymentCard.getCardNumber());
        paymentCard.linkToBankAccount(null);
    }

    public List<PaymentCard> getPaymentCards(){
        return new ArrayList<>(this.paymentCards);
    }

}
