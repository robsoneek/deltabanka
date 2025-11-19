package org.example.bankAccounts.transactions;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TransactionHistoryData {
    @SerializedName("export_date")
    public String exportDate;

    @SerializedName("total_transactions")
    public int totalTransactions;

    @SerializedName("transactions")
    public List<TransactionData> transactions;

    @SerializedName("filter_applied")
    public String filterApplied;
}
