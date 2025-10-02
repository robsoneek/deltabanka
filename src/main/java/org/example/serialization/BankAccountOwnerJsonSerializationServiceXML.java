package org.example.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.example.people.BankAccountOwner;
import org.example.people.serialization.BankAccountOwnerSerialization;
import org.example.people.serialization.BankAccountOwnerSerializationFactory;

import java.io.Serializable;

public class BankAccountOwnerJsonSerializationServiceXML implements Serialization {

    BankAccountOwnerSerializationFactory bankAccountOwnerSerializationFactory = new  BankAccountOwnerSerializationFactory();
    @Override
    public String serialize(Object bankAccountOwner) {
        if(!(bankAccountOwner instanceof BankAccountOwner)){
            throw new IllegalArgumentException("BankAccountOwner must be of type BankAccountOwner");
        }
        BankAccountOwnerSerialization bankAccountOwnerSerialization = bankAccountOwnerSerializationFactory.createBankAccountOwnerSerialization((BankAccountOwner) bankAccountOwner);
        XmlMapper mapper = new XmlMapper();
        String xml = mapper.writeValueAsString(bankAccountOwnerSerialization);
    }

    @Override
    public Object deserialize(String serializedData) {
        return null;
    }
}
