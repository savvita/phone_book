package org.example;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Scanner;

public class ContactBookFileController implements ContactCollection {
    private final String FILENAME = "contacts.txt";
    private final Collection<Contact> contacts = new ArrayList<>();

    public ContactBookFileController() {
        load();
    }

    public Contact[] get() {
        Contact[] contacts = new Contact[this.contacts.size()];
        return this.contacts.toArray(contacts);
    }

    public void create(Contact contact) {
        if(contact != null) {
            contact.setId(findLastId() + 1);
            contacts.add(contact);
        }
    }

    public void create(String name) {
        Contact contact = new Contact(name);
        contact.setId(findLastId() + 1);
        contacts.add(contact);
    }

    public Contact get(int id) {
        return contacts
                .stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(null);
    }
    public Collection<Contact> get(String name) {
        return this.contacts
                .stream()
                .filter(x -> x.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }

    public boolean update(Contact contact) {
        if(contact == null) {
            return false;
        }

        var c = get(contact.getId());
        if(c == null) {
            return false;
        }

        c.setName(contact.getName());
        var phones = contact.getPhones();
        c.clearPhones();
        for(String phone : phones) {
            c.addPhone(phone);
        }

        return true;
    }
    public boolean remove(Contact contact) {
        return contacts.remove(contact);
    }

    public boolean remove(int id) {
        Contact contact = contacts
                .stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(null);
        if(contact == null) {
            return false;
        }

        return contacts.remove(contact);
    }

    private int findLastId() {
        if(contacts.size() == 0) {
            return 0;
        }

        return contacts.stream().max(Comparator.comparingInt(Contact::getId)).get().getId();
    }

    private void save() {
        ObjectMapper Obj = new ObjectMapper();
        try {
            String jsonStr = Obj.writeValueAsString(contacts);
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME));
            writer.write(jsonStr);

            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void load() {
        File file = new File(FILENAME);
        if(!file.exists() || file.isDirectory()) {
            return;
        }

        ObjectMapper Obj = new ObjectMapper();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILENAME));
            Scanner sc = new Scanner(reader);

            StringBuilder sb = new StringBuilder();

            while(sc.hasNextLine()) {
                sb.append(sc.nextLine());
            }

            String str = sb.toString();
            //String str = reader.readLine();
            ArrayList<Contact> contacts = Obj.readValue(str, new TypeReference<>() {
            });
            reader.close();

            this.contacts.clear();
            this.contacts.addAll(contacts);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        save();
    }
}
