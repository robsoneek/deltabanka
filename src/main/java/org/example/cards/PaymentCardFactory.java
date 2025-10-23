package org.example.cards;

import com.google.inject.Inject;

import java.util.UUID;

public class PaymentCardFactory {

    @Inject
    private PaymentCardNumberGenerator paymentCardNumberGenerator;
    @Inject
    private PaymentCardCvvGenerator paymentCardCvvGenerator;
    @Inject
    private PaymentCardPinGenerator paymentCardPinGenerator;
    @Inject
    private PaymentCardExpirationCalculator paymentCardExpirationCalculator;

    public PaymentCard create(){
        String uuid = UUID.randomUUID().toString();
        String cardNumber = this.paymentCardNumberGenerator.generateCardNumber();
        String cvv = this.paymentCardCvvGenerator.generateCvv();
        String pin = this.paymentCardPinGenerator.generatePin();
        String expireMonth = this.paymentCardExpirationCalculator.calculateMonthExpire();
        String expireYear = this.paymentCardExpirationCalculator.calculateYearExpire();

        return new PaymentCard(uuid, cardNumber, cvv, pin, expireMonth, expireYear);

    }

}
