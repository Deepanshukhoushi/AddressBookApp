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
}