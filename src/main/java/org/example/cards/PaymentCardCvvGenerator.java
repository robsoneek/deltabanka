package org.example.cards;

import java.util.Random;

public class PaymentCardCvvGenerator {
    public String generateCvv(){
        Random random = new Random();
        int cvv = random.nextInt(900) + 100;

        return String.valueOf(cvv);
    }
}
