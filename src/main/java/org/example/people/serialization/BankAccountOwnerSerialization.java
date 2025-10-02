package org.example.people.serialization;

import com.google.gson.annotations.SerializedName;

public class BankAccountOwnerSerialization {

    @SerializedName("id")
    public String uuid;

    @SerializedName("jmeno")
    public String firstName;

    @SerializedName("prijmeni")
    public String lastName;

}
