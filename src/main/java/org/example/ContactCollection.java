package org.example;

import java.util.Collection;

public interface ContactCollection {
    Contact[] get();
    Contact get(int id);
    Collection<Contact> get(String name);
    void create(Contact contact);
    boolean update(Contact contact);
    boolean remove(Contact contact);
    boolean remove(int id);
    void close();
}
