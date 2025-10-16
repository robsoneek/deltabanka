package org.example.serialization;

import com.google.gson.Gson;
import org.example.people.Customer;
import org.example.people.serialization.CustomerSerialization;
import org.example.people.serialization.CustomerSerializationFactory;

public class BankAccountOwnerJsonSerializationServiceGson implements Serialization {

    CustomerSerializationFactory customerSerializationFactory = new CustomerSerializationFactory();
    Gson gson = new Gson();

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
