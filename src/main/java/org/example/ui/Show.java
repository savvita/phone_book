package org.example.ui;

import org.example.Contact;
import org.example.ContactCollection;

import java.io.IOException;
import java.util.Collection;
import java.util.function.Consumer;

class Show {
    static void allContacts(ContactCollection book, Consumer<String> out) {
        var contacts = book.get();

        if(contacts.length == 0) {
            out.accept("There are no contacts");
        } else {
            out.accept("Contacts : ");
            for (Contact contact : contacts) {
                out.accept(contact.toString());
            }
        }
    }

    static void contacts(Collection<Contact> contacts, Consumer<String> out) {
        out.accept("Contacts : ");
        for (Contact contact : contacts) {
            out.accept(contact.toString());
        }
    }

    static void phones(String[] phones, Consumer<String> out) {
        out.accept("Phones : ");
        for(int i = 0, length = phones.length; i < length; i++) {
            out.accept((i + 1) + " . " + phones[i]);
        }
    }

    static void phone(String phone, Consumer<String> out) {
        out.accept("Phone : " + phone);
    }

    static void contact(Contact contact, Consumer<String> out) {
        out.accept("Contact : ");
        out.accept(contact.toString());
    }

    static void cls(){
        try {

            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c",
                        "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
    }
}
