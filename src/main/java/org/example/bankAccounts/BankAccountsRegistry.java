package org.example.bankAccounts;

import java.util.ArrayList;
import java.util.List;

public class BankAccountsRegistry {
    private List<BankAccountWithPaymentCards> bankAccountsWithPaymentCards = new ArrayList<>();
    private List<SavingBankAccount> savingBankAccounts = new ArrayList<>();
    private List<BaseBankAccount> baseBankAccounts = new ArrayList<>();
    private List<StudentBankAccount> studentBankAccounts = new ArrayList<>();

    public void addBaseBankAccount(BaseBankAccount account) {
        baseBankAccounts.add(account);
    }

    public void removeBaseBankAccount(BaseBankAccount account) {
        baseBankAccounts.remove(account);
    }

    public List<BaseBankAccount> getAllBaseBankAccounts() {
        return new ArrayList<>(baseBankAccounts);
    }

    public void addSavingBankAccount(SavingBankAccount account) {
        savingBankAccounts.add(account);
    }

    public void removeSavingBankAccount(SavingBankAccount account) {
        savingBankAccounts.remove(account);
    }

    public List<SavingBankAccount> getAllSavingBankAccounts() {
        return new ArrayList<>(savingBankAccounts);
    }

    public void addStudentBankAccount(StudentBankAccount account) {
        studentBankAccounts.add(account);
    }

    public void removeStudentBankAccount(StudentBankAccount account) {
        studentBankAccounts.remove(account);
    }

    public List<StudentBankAccount> getAllStudentBankAccounts() {
        return new ArrayList<>(studentBankAccounts);
    }

    public void addBankAccountWithPaymentCards(BankAccountWithPaymentCards account) {
        bankAccountsWithPaymentCards.add(account);
    }

    public void removeBankAccountWithPaymentCards(BankAccountWithPaymentCards account) {
        bankAccountsWithPaymentCards.remove(account);
    }

    public List<BankAccountWithPaymentCards> getAllBankAccountsWithPaymentCards() {
        return new ArrayList<>(bankAccountsWithPaymentCards);
    }

    public BaseBankAccount findByAccountNumber(String accountNumber) {
        for (BaseBankAccount account : baseBankAccounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        for (SavingBankAccount account : savingBankAccounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        for (StudentBankAccount account : studentBankAccounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        for (BankAccountWithPaymentCards account : bankAccountsWithPaymentCards) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }
}
