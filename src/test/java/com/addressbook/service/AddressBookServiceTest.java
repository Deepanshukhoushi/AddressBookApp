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
    
    @Test
    public void givenContact_whenUpdated_shouldSyncWithDB() {

        AddressBookService service = new AddressBookService();

        String name = "Deepanshu";
        String newCity = "Indore";

        Contact contactInMemory = service.updateContactCity(name, newCity);

        AddressBookDBService dbService = new AddressBookDBService();

        List<Contact> contacts = dbService.getAllContactsFromDB();

        Contact contactFromDB = contacts.stream()
                .filter(c -> c.getFirstName().equals(name))
                .findFirst()
                .orElse(null);

    }
}