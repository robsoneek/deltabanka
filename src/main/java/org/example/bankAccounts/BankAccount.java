package org.example.bankAccounts;

import org.example.cards.PaymentCardRegistry;
import org.example.people.Customer;

public class BankAccount extends BankAccountWithPaymentCards {

    public BankAccount(String uuid, String accountNumber, Customer customer) {
        super(uuid,accountNumber, customer, 0);
    }
}
