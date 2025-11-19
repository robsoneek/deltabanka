package org.example.bankAccounts.transactions;

public enum TransactionType {
    DEPOSIT("Vklad"),
    WITHDRAWAL("Vyber"),
    CARD_PAYMENT("Platba kartou"),
    INTEREST("Uroceni"),
    TRANSFER("Prevod");

    private final String name;

    TransactionType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}