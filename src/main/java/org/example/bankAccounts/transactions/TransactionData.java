package org.example.bankAccounts.transactions;

import com.google.gson.annotations.SerializedName;

public class TransactionData {
    @SerializedName("transaction_id")
    public String transactionId;

    @SerializedName("date")
    public String transactionDate;

    @SerializedName("recipient_account")
    public String recipientAccountNumber;

    @SerializedName("sender_account")
    public String senderAccountNumber;

    @SerializedName("type")
    public String transactionType;

    @SerializedName("amount")
    public double amount;

    @SerializedName("description")
    public String description;
}
