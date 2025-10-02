package org.example.serialization;

import com.google.gson.Gson;
import org.example.people.BankAccountOwner;
import org.example.people.serialization.BankAccountOwnerSerialization;
import org.example.people.serialization.BankAccountOwnerSerializationFactory;

public class BankAccountOwnerJsonSerializationServiceGson implements Serialization {

    BankAccountOwnerSerializationFactory bankAccountOwnerSerializationFactory = new BankAccountOwnerSerializationFactory();
    Gson gson = new Gson();

    @Override
    public String serialize(Object bankAccountOwner) {
        if(!(bankAccountOwner instanceof BankAccountOwner)){
            throw new IllegalArgumentException("BankAccountOwner must be of type BankAccountOwner");
        }

        BankAccountOwnerSerialization bankAccountOwnerSerialization = bankAccountOwnerSerializationFactory.createBankAccountOwnerSerialization((BankAccountOwner) bankAccountOwner);

        return gson.toJson(bankAccountOwnerSerialization);
    }

    @Override
    public Object deserialize(String serializedData) {
        return gson.fromJson("", BankAccountOwnerSerialization.class);
    }

}
