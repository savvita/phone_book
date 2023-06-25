package org.example;

public class ContactBookBuilder {
    private ContactBookBuilder() {}

    public static ContactCollection createContactBookFileController() {
        return new ContactBookFileController();
    }
}
