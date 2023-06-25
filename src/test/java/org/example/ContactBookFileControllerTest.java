package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class ContactBookFileControllerTest {
    @Test
    public void testAddContact() {
        ContactCollection book = ContactBookBuilder.createContactBookFileController();
        int count = book.get().length;
        book.create(ContactBuilder.createContact());
        assertEquals(count + 1, book.get().length);
    }

    @Test
    public void testGetContactById() {
        ContactCollection book = ContactBookBuilder.createContactBookFileController();
        Contact contact = book.get(1);
        assertNotEquals(null, contact);
    }

    @Test
    public void testGetContactByIdFail() {
        ContactCollection book = ContactBookBuilder.createContactBookFileController();
        Contact contact = book.get(-1);
        assertNull(contact);
    }

    @Test
    public void testGetContactByName() {
        ContactCollection book = ContactBookBuilder.createContactBookFileController();
        String name = "Test";
        book.create(ContactBuilder.createContact(name));
        var contacts = book.get(name);
        assertNotEquals(0, contacts.size());
    }

    @Test
    public void testGetContactByNameFail() {
        ContactCollection book = ContactBookBuilder.createContactBookFileController();
        String name = "Something weird";
        book.create(ContactBuilder.createContact());
        var contacts = book.get(name);
        assertEquals(0, contacts.size());
    }

    @Test
    public void testUpdateContact() {
        ContactCollection book = ContactBookBuilder.createContactBookFileController();
        int count = 3;

        Contact contact = ContactBuilder.createContact();
        ContactBuilder.addPhones(contact, count);
        book.create(contact);

        String newName = "New name";
        int newCount = 1;
        Contact updatedContact = ContactBuilder.createContact();
        updatedContact.setId(contact.getId());
        updatedContact.setName(newName);
        ContactBuilder.addPhones(updatedContact, newCount);

        boolean res = book.update(updatedContact);
        assertTrue(res);

        Contact c = book.get(contact.getId());
        assertEquals(newName, c.getName());
        assertEquals(newCount, c.getPhones().length);
    }

    @Test
    public void testUpdateContactFail() {
        ContactCollection book = ContactBookBuilder.createContactBookFileController();
        int count = 3;

        Contact contact = ContactBuilder.createContact();
        ContactBuilder.addPhones(contact, count);
        book.create(contact);

        String name = contact.getName();

        String newName = "New name";
        int newCount = 1;
        Contact updatedContact = ContactBuilder.createContact();
        updatedContact.setId(-1);
        updatedContact.setName(newName);
        ContactBuilder.addPhones(updatedContact, newCount);

        boolean res = book.update(updatedContact);
        assertFalse(res);

        Contact c = book.get(contact.getId());
        assertEquals(name, c.getName());
        assertEquals(count, c.getPhones().length);
    }

    @Test
    public void testRemoveContact() {
        ContactCollection book = ContactBookBuilder.createContactBookFileController();

        Contact contact = ContactBuilder.createContact();
        book.create(contact);
        int count = book.get().length;

        Contact contactToRemove = book.get(contact.getId());

        boolean res = book.remove(contactToRemove);
        assertTrue(res);
        assertEquals(count - 1, book.get().length);
    }

    @Test
    public void testRemoveContactFail() {
        ContactCollection book = ContactBookBuilder.createContactBookFileController();

        Contact contact = ContactBuilder.createContact();
        book.create(contact);
        int count = book.get().length;

        Contact contactToRemove = ContactBuilder.createContact();

        boolean res = book.remove(contactToRemove);
        assertFalse(res);
        assertEquals(count, book.get().length);
    }

    @Test
    public void testRemoveContactById() {
        ContactCollection book = ContactBookBuilder.createContactBookFileController();

        Contact contact = ContactBuilder.createContact();
        book.create(contact);
        int count = book.get().length;

        boolean res = book.remove(contact.getId());
        assertTrue(res);
        assertEquals(count - 1, book.get().length);
    }

    @Test
    public void testRemoveContactByIdFail() {
        ContactCollection book = ContactBookBuilder.createContactBookFileController();

        Contact contact = ContactBuilder.createContact();
        book.create(contact);
        int count = book.get().length;

        boolean res = book.remove(-1);
        assertFalse(res);
        assertEquals(count, book.get().length);
    }
}