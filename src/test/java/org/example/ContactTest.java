package org.example;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ContactTest {
    @Test
    public void testSetName() {
        Contact contact = ContactBuilder.createContact();
        final String name = "Vasyl";
        contact.setName(name);
        assertEquals(name, contact.getName());
    }

    @Test
    public void testSetNameFail() {
        Contact contact = ContactBuilder.createContact();
        final String name = "     ";
        contact.setName(name);
        assertNotEquals(name, contact.getName());
    }

    @Test
    public void testAddPhone() {
        Contact contact = ContactBuilder.createContact();
        final int count = 3;
        ContactBuilder.addPhones(contact, count);
        assertEquals(count, contact.getPhones().length);
    }

    @Test
    public void testAddPhoneFail() {
        Contact contact = ContactBuilder.createContact();
        contact.addPhone("         ");
        assertEquals(0, contact.getPhones().length);
    }

    @Test
    public void testRemovePhoneByNumber() {
        Contact contact = ContactBuilder.createContact();
        final int count = 3;
        ContactBuilder.addPhones(contact, count);

        String phone = "0000000000";
        contact.addPhone(phone);

        boolean res = contact.removePhone(phone);
        assertTrue(res);
        assertEquals(count, contact.getPhones().length);
    }

    @Test
    public void testRemovePhoneByIdx() {
        Contact contact = ContactBuilder.createContact();
        final int count = 3;
        ContactBuilder.addPhones(contact, count);

        boolean res = contact.removePhone(1);
        assertTrue(res);
        assertEquals(count - 1, contact.getPhones().length);
    }

    @Test
    public void testRemovePhoneByNumberFail() {
        Contact contact = ContactBuilder.createContact();
        final int count = 3;
        ContactBuilder.addPhones(contact, count);

        String phone = "0000000000";
        contact.addPhone(phone);

        boolean res = contact.removePhone("9999999999");
        assertFalse(res);
        assertEquals(count + 1, contact.getPhones().length);
    }

    @Test
    public void testRemovePhoneByIdxFail() {
        Contact contact = ContactBuilder.createContact();
        final int count = 3;
        ContactBuilder.addPhones(contact, count);

        boolean res = contact.removePhone(3);
        assertFalse(res);
        assertEquals(count, contact.getPhones().length);
    }

    @Test
    public void testEditPhone() {
        Contact contact = ContactBuilder.createContact();

        String phone = "0000000000";
        contact.addPhone(phone);

        String newPhone = "9999999999";

        boolean res = contact.editPhone(0, newPhone);
        assertTrue(res);
        assertEquals(newPhone, contact.getPhones()[0]);
    }
    @Test
    public void testEditPhoneFail() {
        Contact contact = ContactBuilder.createContact();

        String phone = "0000000000";
        contact.addPhone(phone);

        String newPhone = "9999999999";

        boolean res = contact.editPhone(1, newPhone);
        assertFalse(res);
        String ph = Arrays.stream(contact.getPhones()).filter(x -> x.equals(newPhone)).findFirst().orElse(null);
        assertEquals(null, ph);
    }

    @Test
    public void testClearPhones() {
        Contact contact = ContactBuilder.createContact();
        final int count = 3;
        ContactBuilder.addPhones(contact, count);

        contact.clearPhones();
        assertEquals(0, contact.getPhones().length);
    }
}