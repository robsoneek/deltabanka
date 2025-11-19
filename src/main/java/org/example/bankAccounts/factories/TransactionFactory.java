package org.example.bankAccounts.factories;

import org.example.bankAccounts.BankAccount;
import org.example.bankAccounts.transactions.Transaction;
import org.example.bankAccounts.transactions.TransactionType;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public class TransactionFactory {

    public Transaction createDeposit(String accountNumber, double amount) {
        return new Transaction(
                generateTransactionId(),
                LocalDateTime.now(),
                accountNumber,
                null,
                TransactionType.DEPOSIT,
                amount,
                "Vklad na ucet"
        );
    }

    public Transaction createWithdrawal(String accountNumber, double amount) {
        return new Transaction(
                generateTransactionId(),
                LocalDateTime.now(),
                null,
                accountNumber,
                TransactionType.WITHDRAWAL,
                amount,
                "Vyber z uctu"
        );
    }

    public Transaction createCardPayment(String cardNumber, String accountNumber, double amount) {
        return new Transaction(
                generateTransactionId(),
                LocalDateTime.now(),
                null,
                accountNumber,
                TransactionType.CARD_PAYMENT,
                amount,
                "Platba kartou ****" + cardNumber.substring(cardNumber.length() - 4)
        );
    }

    public Transaction createInterest(String accountNumber, double amount, float interestRate) {
        return new Transaction(
                generateTransactionId(),
                LocalDateTime.now(),
                accountNumber,
                null,
                TransactionType.INTEREST,
                amount,
                String.format("Uroceni %.2f%%", interestRate)
        );
    }

    public Transaction createTransfer(String senderAccount, String recipientAccount, double amount) {
        return new Transaction(
                generateTransactionId(),
                LocalDateTime.now(),
                recipientAccount,
                senderAccount,
                TransactionType.TRANSFER,
                amount,
                "Prevod mezi ucty"
        );
    }

    private String generateTransactionId() {
        return "TXN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
