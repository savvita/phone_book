package org.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Contact {
    private String name;
    private final List<String> phones = new ArrayList<>();
    private int id;

    public Contact() {}

    public Contact(String name) {
        setName(name);
    }

    public Contact(String name, Collection<String> phones) {
        this(name);
        for(String phone : phones) {
            addPhone(phone);
        }
    }

    public String getName() {
        return this.name;
    }

    public String[] getPhones() {
        String[] phones = new String[this.phones.size()];
        return this.phones.toArray(phones);
    }

    public void setName(String name) {
        if(name != null && name.trim().length() > 0) {
            this.name = name.trim();
        }
    }

    public void addPhone(String phone) {
        if(phone == null || phone.trim().length() == 0) {
            return;
        }
        this.phones.add(phone.trim());
    }

    public boolean removePhone(String phone) {
        if(phone == null || phone.trim().length() == 0) {
            return false;
        }

        var ph = phones.stream().filter(x -> x.equals(phone.trim())).findFirst().orElse(null);
        if(ph == null) return false;

        return this.phones.remove(ph);
    }

    public boolean editPhone(int idx, String phone) {
        if(phone == null || phone.trim().length() == 0) {
            return false;
        }

        if(idx < 0 || idx >= phones.size()) {
            return false;
        }

        phones.set(idx, phone);
        return true;
    }

    public boolean removePhone(int idx) {
        if(idx < 0 || idx >= phones.size()) {
            return false;
        }

        this.phones.remove(idx);
        return true;
    }

    public void clearPhones() {
        phones.clear();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("%d. %s (%s)", id, name, phones);
    }
}
