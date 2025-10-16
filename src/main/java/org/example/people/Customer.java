package org.example.people;

public class Customer{

    private final String uuid;
    private final String firstName;
    private final String lastName;

    public Customer(String uuid, String firstName, String lastName) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUuid() {return uuid;}
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getFullName() {return String.format("%s %s", firstName, lastName);}

}
