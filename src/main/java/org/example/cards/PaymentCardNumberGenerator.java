package org.example.cards;

import java.util.Random;

public class PaymentCardNumberGenerator {
    public String generateCardNumber(){
        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder();

        for (int i = 0; i < 15; i++) {
            cardNumber.append(random.nextInt(10));
        }
        cardNumber.append("0");

        return cardNumber.toString();
    }
}
