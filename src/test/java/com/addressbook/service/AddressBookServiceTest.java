package com.addressbook.service;

import com.addressbook.model.Contact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AddressBookServiceTest {

    @Test
    public void givenDBEntries_whenRetrieved_shouldMatchContactCount() {

        AddressBookDBService dbService = new AddressBookDBService();

        List<Contact> contacts = dbService.getAllContactsFromDB();

        Assertions.assertTrue(contacts.size() >= 3);
    }
}