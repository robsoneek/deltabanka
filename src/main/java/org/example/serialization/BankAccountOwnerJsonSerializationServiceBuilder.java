package org.example.serialization;

import com.google.gson.Gson;
import org.example.people.BankAccountOwner;
import org.example.people.serialization.BankAccountOwnerSerialization;
import org.example.people.serialization.BankAccountOwnerSerializationFactory;

public class BankAccountOwnerJsonSerializationServiceBuilder implements Serialization {

    BankAccountOwnerSerializationFactory bankAccountOwnerSerializationFactory = new BankAccountOwnerSerializationFactory();


    @Override
    public String serialize(Object bankAccountOwner) {
        if(!(bankAccountOwner instanceof BankAccountOwner)){
            throw new IllegalArgumentException("BankAccountOwner must be of type BankAccountOwner");
        }

        BankAccountOwnerSerialization bankAccountOwnerSerialization = bankAccountOwnerSerializationFactory.createBankAccountOwnerSerialization((BankAccountOwner) bankAccountOwner);

        StringBuilder builder = new StringBuilder();

        builder.append("{");
        builder.append("\"uuid\":\"");
        builder.append(bankAccountOwnerSerialization.uuid);
        builder.append("\"firstName\":\"");
        builder.append(bankAccountOwnerSerialization.firstName);
        builder.append("\"lastName\":\"");
        builder.append(bankAccountOwnerSerialization.lastName);
        builder.append("}");

        return builder.toString();
    }

    @Override
    public Object deserialize(String serializedData) {
        return null;
    }

}
