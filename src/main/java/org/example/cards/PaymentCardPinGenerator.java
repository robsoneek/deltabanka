package org.example.cards;

import java.util.Random;

public class PaymentCardPinGenerator {
    public String generatePin(){
        Random random = new Random();
        int pin = random.nextInt(10000);

        return String.format("%04d", pin);
    }
}
