package org.example.people;

public class BaseBankAccountOwner {

    private String uuid;

    private String firstName;
    private String lastName;

    public BaseBankAccountOwner(String uuid, String firstName, String lastName) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
