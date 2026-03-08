package com.addressbook.service;

import com.addressbook.dto.AddressBookDTO;
import com.addressbook.model.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressBookService {

    private static final Logger log = LoggerFactory.getLogger(AddressBookService.class);

    private List<Contact> contactList = new ArrayList<>();

    public Contact addContact(AddressBookDTO dto) {

        log.info("Adding new contact: {}", dto.getFirstName());

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

        contactList.add(contact);

        return contact;
    }
    
    public Contact updateContact(String firstName, AddressBookDTO dto) {

        log.info("Updating contact: {}", firstName);

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
    
    public boolean deleteContact(String firstName) {

        log.info("Deleting contact: {}", firstName);

        return contactList.removeIf(contact ->
                contact.getFirstName().equalsIgnoreCase(firstName));
    }
}