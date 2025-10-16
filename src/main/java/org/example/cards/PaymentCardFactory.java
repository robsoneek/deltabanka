package org.example.cards;

import java.util.UUID;

public class PaymentCardFactory {

    private PaymentCardNumberGenerator paymentCardNumberGenerator = new PaymentCardNumberGenerator();
    private PaymentCardCvvGenerator paymentCardCvvGenerator = new PaymentCardCvvGenerator();
    private PaymentCardPinGenerator paymentCardPinGenerator = new PaymentCardPinGenerator();
    private PaymentCardExpirationCalculator paymentCardExpirationCalculator = new PaymentCardExpirationCalculator();

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
