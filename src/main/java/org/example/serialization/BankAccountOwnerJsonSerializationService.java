package org.example.serialization;

import com.google.gson.Gson;
import com.google.inject.Inject;
import org.example.people.Customer;
import org.example.people.serialization.CustomerSerialization;
import org.example.people.serialization.CustomerSerializationFactory;

public class BankAccountOwnerJsonSerializationService implements Serialization {

    @Inject
    private CustomerSerializationFactory customerSerializationFactory;
    @Inject
    private Gson gson;


    @Override
    public String serialize(Object customer) {
        if(!(customer instanceof Customer)){
            throw new IllegalArgumentException("BankAccountOwner must be of type BankAccountOwner");
        }

        CustomerSerialization bankAccountOwnerSerialization =
                customerSerializationFactory.createBankAccountOwnerSerialization((Customer)customer);

        return gson.toJson(bankAccountOwnerSerialization);
    }

    @Override
    public Object deserialize(String serializedData) {
        return gson.fromJson("", CustomerSerialization.class);
    }

}
