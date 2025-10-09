package org.example.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.example.people.BankAccountOwner;
import org.example.people.serialization.BankAccountOwnerSerialization;
import org.example.people.serialization.BankAccountOwnerSerializationFactory;

import java.io.Serializable;

public class BankAccountOwnerJsonSerializationServiceXML implements Serialization {

    private final XmlMapper xmlMapper = new XmlMapper();
    BankAccountOwnerSerializationFactory bankAccountOwnerSerializationFactory = new  BankAccountOwnerSerializationFactory();

    @Override
    public String serialize(Object bankAccountOwner) {
        if(!(bankAccountOwner instanceof BankAccountOwner)){
            throw new IllegalArgumentException("BankAccountOwner must be of type BankAccountOwner");
        }
        BankAccountOwnerSerialization bankAccountOwnerSerialization = bankAccountOwnerSerializationFactory.createBankAccountOwnerSerialization((BankAccountOwner) bankAccountOwner);
        try {
            return xmlMapper.writeValueAsString(bankAccountOwnerSerialization);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize BankAccountOwner to XML using Jackson", e);
        }

    }

    @Override
    public Object deserialize(String serializedData) {
        try {
            BankAccountOwnerSerialization serializationDto = xmlMapper.readValue(serializedData, BankAccountOwnerSerialization.class);

            return new BankAccountOwner(
                    serializationDto.uuid,
                    serializationDto.firstName,
                    serializationDto.lastName
            );
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Failed to deserialize XML to BankAccountOwner. XML format may be invalid.", e);
        }

    }
}
