package org.example.ui;

import org.example.Contact;
import org.example.ContactCollection;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class UI {
    public static void start(ContactCollection book, Consumer<String> out, Supplier<String> in) {
        do {
            Show.cls();
            Menu.showMainMenu(out);
            var input = in.get();
            if(input.length() == 0) continue;

            if(input.charAt(0) == '0') break;

            switch (input.charAt(0)) {
                case '1' ->
                    // show all
                        Show.allContacts(book, out);
                case '2' ->
                    // add new contact
                        addContact(book, out, in);
                case '3' ->
                    // edit contact
                        editContact(book, out, in);
                case '4' ->
                    // remove contact
                        removeContact(book, out, in);
                case '5' ->
                    // find contact
                        findContacts(book, out, in);
            }
            out.accept("Press ENTER to continue");
            in.get();
        } while(true);
    }

    private static boolean addPhone(Contact contact, String phone) {
        if(phone != null && phone.length() != 0) {
            contact.addPhone(phone);
            return true;
        }

        return false;
    }

    private static void addContact(ContactCollection book, Consumer<String> out, Supplier<String> in) {
        Contact contact = new Contact();

        String name = In.name(out, in);
        contact.setName(name);
        do {
            if(!addPhone(contact, In.phone(out, in))) break;
        } while(true);

        book.create(contact);
        out.accept("Added...");
    }

    private static void removeContact(ContactCollection book, Consumer<String> out, Supplier<String> in) {
        Show.allContacts(book, out);
        int id;

        try {
            id = In.id(out, in);
        } catch (Exception e) {
            out.accept("Invalid number");
            return;
        }

        Contact contact = book.get(id);

        if(contact == null) {
            out.accept("Contact not found");
            return;
        }

        book.remove(id);
        out.accept("Removed...");
    }

    private static Contact editName(Contact contact, Consumer<String> out, Supplier<String> in) {
        String name = In.name(out, in);
        contact.setName(name);
        return contact;
    }

    private static void editPhone(Contact contact, Consumer<String> out, Supplier<String> in) {
        try {
            int idx = In.id(out, in);
            String phone = In.phone(out, in);
            if(contact.editPhone(idx - 1, phone)) {
                out.accept("Edited...");
            } else {
                out.accept("Phone not found");
            }
        } catch (Exception ex) {
            out.accept("Invalid value");
        }
    }

    private static Contact editPhones(Contact contact, Consumer<String> out, Supplier<String> in) {
        do {
            var phones = contact.getPhones();
            Show.phones(phones, out);
            Menu.showEditPhonesMenu(out);
            String input = in.get();
            if(input.length() == 0) continue;

            if(input.charAt(0) == '0') break;

            switch (input.charAt(0)) {
                case '1' -> {
                    // add new phone
                    addPhone(contact, In.phone(out, in));
                    out.accept("Added...");
                }
                case '2' ->
                    // edit phone
                        editPhone(contact, out, in);
                case '3' ->
                    // remove phone
                        removePhone(contact, out, in);
            }
            out.accept("Press ENTER to continue");
            in.get();
        } while(true);

        return contact;
    }

    private static void removePhone(Contact contact, Consumer<String> out, Supplier<String> in) {
        try {
            int idx = In.id(out, in);
            if(contact.removePhone(idx - 1)) {
                out.accept("Removed...");
            } else {
                out.accept("Phone not found");
            }
        } catch (Exception ex) {
            out.accept("Invalid value");
        }
    }

    private static void editContact(ContactCollection book, Consumer<String> out, Supplier<String> in) {
        Show.allContacts(book, out);

        int id;

        try {
            id = In.id(out, in);
        } catch (Exception e) {
            out.accept("Invalid number");
            return;
        }

        Contact contact = book.get(id);

        if(contact == null) {
            out.accept("Contact not found");
            return;
        }

        do {
            Show.contact(contact, out);
            Menu.showEditMenu(out);
            String input = in.get();
            if(input.length() == 0) continue;

            if(input.charAt(0) == '0') break;

            switch (input.charAt(0)) {
                case '1' -> {
                    // edit name
                    editName(contact, out, in);
                    out.accept("Edited...");
                }
                case '2' -> {
                    // clear phones
                    contact.clearPhones();
                    out.accept("Cleared...");
                }
                case '3' ->
                    // edit phones
                        editPhones(contact, out, in);
            }
            out.accept("Press ENTER to continue");
            in.get();
        } while (true);

        book.update(contact);
        out.accept("Updated...");
    }

    private static void findContacts(ContactCollection book, Consumer<String> out, Supplier<String> in) {
        String name = In.name(out, in);
        if(name == null || name.trim().length() == 0) {
            out.accept("Invalid input");
            return;
        }

        var contacts = book.get(name);
        if(contacts.size() > 0) {
            Show.contacts(contacts, out);
        } else {
            out.accept("Not found");
        }
    }
}
