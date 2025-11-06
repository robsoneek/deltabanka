package org.example.bankAccounts;

import com.google.inject.Inject;
import org.example.cards.PaymentCard;
import org.example.cards.PaymentCardRegistry;
import org.example.people.Customer;


import java.util.List;


public class BankAccountWithPaymentCards extends BaseBankAccount{

    @Inject
    private PaymentCardRegistry paymentCardRegistry;

    public BankAccountWithPaymentCards(String uuid, String bankAccountNumber,
                                       Customer customer, double balance,
                                       PaymentCardRegistry paymentCardRegistry){
        super(uuid, bankAccountNumber, customer, balance);
        this.paymentCardRegistry = paymentCardRegistry;
    }

    public BankAccountWithPaymentCards(String uuid, String bankAccountNumber,
                                       Customer customer, double balance){
        super(uuid, bankAccountNumber, customer, balance);
        this.paymentCardRegistry = new PaymentCardRegistry();
    }

    public void addPaymentCard(PaymentCard paymentCard){
        paymentCardRegistry.addCard(paymentCard);
        paymentCard.linkToBankAccount(this);
    }

    public void removePaymentCard(PaymentCard paymentCard) {
        paymentCardRegistry.removeCard(paymentCard);
        paymentCard.linkToBankAccount(null);
    }

    public List<PaymentCard> getPaymentCards(){ return paymentCardRegistry.getAllCards();}

}
