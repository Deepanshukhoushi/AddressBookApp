package com.addressbook.service;

import com.addressbook.dto.AddressBookDTO;
import com.addressbook.model.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AddressBookService {

    private static final Logger log = LoggerFactory.getLogger(AddressBookService.class);

    // Map to store multiple AddressBooks
    private Map<String, List<Contact>> addressBookMap = new HashMap<>();


    // Create AddressBook if it does not exist
    private void createAddressBookIfNotExists(String bookName) {

        if (!addressBookMap.containsKey(bookName)) {

            addressBookMap.put(bookName, new ArrayList<>());

            log.info("New Address Book created: {}", bookName);
        }
    }


    // Add Contact to a specific AddressBook
    public Contact addContact(String bookName, AddressBookDTO dto) {

        createAddressBookIfNotExists(bookName);

        log.info("Adding new contact: {} to {}", dto.getFirstName(), bookName);

        Contact contact = new Contact(
                dto.getFirstName(),
                dto.getLastName(),
                dto.getAddress(),
                dto.getCity(),
                dto.getState(),
                dto.getZip(),
                dto.getPhoneNumber(),
                dto.getEmail()
        );

        addressBookMap.get(bookName).add(contact);

        log.info("Total contacts in {}: {}", bookName,
                addressBookMap.get(bookName).size());

        return contact;
    }


    // Update contact in specific AddressBook
    public Contact updateContact(String bookName, String firstName, AddressBookDTO dto) {

        List<Contact> contactList = addressBookMap.get(bookName);

        if (contactList == null) {
            return null;
        }

        log.info("Updating contact: {} in {}", firstName, bookName);

        for (Contact contact : contactList) {

            if (contact.getFirstName().equalsIgnoreCase(firstName)) {

                contact.setFirstName(dto.getFirstName());
                contact.setLastName(dto.getLastName());
                contact.setAddress(dto.getAddress());
                contact.setCity(dto.getCity());
                contact.setState(dto.getState());
                contact.setZip(dto.getZip());
                contact.setPhoneNumber(dto.getPhoneNumber());
                contact.setEmail(dto.getEmail());

                return contact;
            }
        }

        return null;
    }


    // Delete contact from AddressBook
    public boolean deleteContact(String bookName, String firstName) {

        List<Contact> contactList = addressBookMap.get(bookName);

        if (contactList == null) {
            return false;
        }

        log.info("Deleting contact: {} from {}", firstName, bookName);

        return contactList.removeIf(contact ->
                contact.getFirstName().equalsIgnoreCase(firstName));
    }


    // Get contacts from specific AddressBook
    public List<Contact> getContacts(String bookName) {

        return addressBookMap.getOrDefault(bookName, new ArrayList<>());
    }
}