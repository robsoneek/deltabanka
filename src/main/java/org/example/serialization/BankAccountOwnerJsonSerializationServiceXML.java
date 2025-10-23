package org.example.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.inject.Inject;
import org.example.people.Customer;
import org.example.people.serialization.CustomerSerialization;
import org.example.people.serialization.CustomerSerializationFactory;


public class BankAccountOwnerJsonSerializationServiceXML implements Serialization {

    @Inject
    private XmlMapper xmlMapper;
    @Inject
    private CustomerSerializationFactory customerSerializationFactory;

    @Override
    public String serialize(Object customer) {
        if(!(customer instanceof Customer)){
            throw new IllegalArgumentException("Customer must be of type Customer");
        }
        CustomerSerialization bankAccountOwnerSerialization =
                customerSerializationFactory.createBankAccountOwnerSerialization((Customer) customer);
        try {
            return xmlMapper.writeValueAsString(bankAccountOwnerSerialization);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize Customer to XML using Jackson", e);
        }

    }

    @Override
    public Object deserialize(String serializedData) {
        try {
            CustomerSerialization serializationDto = xmlMapper.readValue(serializedData, CustomerSerialization.class);

            return new Customer(
                    serializationDto.uuid,
                    serializationDto.firstName,
                    serializationDto.lastName
            );
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Failed to deserialize XML to BankAccountOwner. XML format may be invalid.", e);
        }

    }
}
