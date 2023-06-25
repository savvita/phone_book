package org.example;

public class ContactBuilder {
    private ContactBuilder() {}
    public static Contact createContact() {
        Contact contact = new Contact("Test name");
        contact.setId(1);
        return contact;
    }

    public static Contact createContact(String name) {
        Contact contact = new Contact(name);
        contact.setId(1);
        return contact;
    }

    private static String getRandomPhone() {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < 10; i++) {
            sb.append((char)('0' + Math.random() * ('9' - '0' + 1)));
        }

        return sb.toString();
    }

    public static Contact addPhones(Contact contact, int count) {
        for(int i = 0; i < count; i++) {
            contact.addPhone(getRandomPhone());
        }
        return contact;
    }
}
