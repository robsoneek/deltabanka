package org.example.serialization;

import org.example.people.Customer;
import org.example.people.serialization.CustomerSerialization;
import org.example.people.serialization.CustomerSerializationFactory;

public class CustomerJsonSerializationServiceBuilder implements Serialization {

    CustomerSerializationFactory customerSerializationFactory = new CustomerSerializationFactory();


    @Override
    public String serialize(Object customer) {
        if(!(customer instanceof Customer)){
            throw new IllegalArgumentException("BankAccountOwner must be of type BankAccountOwner");
        }

        CustomerSerialization bankAccountOwnerSerialization =
                customerSerializationFactory.createBankAccountOwnerSerialization((Customer) customer);

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
