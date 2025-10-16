package org.example.people.serialization;


import org.example.people.Customer;

public class CustomerSerializationFactory {
    public CustomerSerialization createBankAccountOwnerSerialization(Customer customer){
        CustomerSerialization customerSerialization = new CustomerSerialization();

        customerSerialization.uuid = customer.getUuid();
        customerSerialization.firstName = customer.getFirstName();
        customerSerialization.lastName = customer.getLastName();

        return customerSerialization;
    }
}
