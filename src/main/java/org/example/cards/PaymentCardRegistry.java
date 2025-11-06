package org.example.cards;

import java.util.ArrayList;
import java.util.List;

public class PaymentCardRegistry {

    private List<PaymentCard> cards = new ArrayList<>();

    public void addCard(PaymentCard card) {
        cards.add(card);
    }

    public void removeCard(PaymentCard card) {
        cards.remove(card);
    }

    public List<PaymentCard> getAllCards() {
        return new ArrayList<>(cards);
    }

    public PaymentCard findByCardNumber(String cardNumber) {
        for (PaymentCard card : cards) {
            if (card.getCardNumber().equals(cardNumber)) {
                return card;
            }
        }
        return null;
    }
}
