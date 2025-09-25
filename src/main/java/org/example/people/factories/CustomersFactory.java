package org.example.people.factories;

import org.example.people.Customer;

public class CustomersFactory {

    public Customer customer(String uuid, String firstName, String lastName){
        return new Customer(uuid, firstName, lastName);

    }
}
