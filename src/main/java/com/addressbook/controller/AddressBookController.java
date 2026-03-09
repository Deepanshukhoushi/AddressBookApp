package com.addressbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.addressbook.dto.AddressBookDTO;
import com.addressbook.model.Contact;
import com.addressbook.service.AddressBookService;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {

    @Autowired
    private AddressBookService service;

    // Create contact in specific AddressBook
    @PostMapping("/{addressBookName}/create")
    public ResponseEntity<Contact> createAddressBookData(
            @PathVariable String addressBookName,
            @RequestBody AddressBookDTO dto) {

        Contact contact = service.addContact(addressBookName, dto);

        return ResponseEntity.ok(contact);
    }


    // Update contact in specific AddressBook
    @PutMapping("/{addressBookName}/update/{firstName}")
    public ResponseEntity<Contact> updateAddressBookData(
            @PathVariable String addressBookName,
            @PathVariable String firstName,
            @RequestBody AddressBookDTO dto) {

        Contact updatedContact = service.updateContact(addressBookName, firstName, dto);

        if (updatedContact != null) {
            return ResponseEntity.ok(updatedContact);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // Delete contact from specific AddressBook
    @DeleteMapping("/{addressBookName}/delete/{firstName}")
    public ResponseEntity<String> deleteAddressBookData(
            @PathVariable String addressBookName,
            @PathVariable String firstName) {

        boolean deleted = service.deleteContact(addressBookName, firstName);

        if (deleted) {
            return ResponseEntity.ok("Deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // Get all contacts from specific AddressBook
    @GetMapping("/{addressBookName}/get")
    public ResponseEntity<List<Contact>> getContacts(
            @PathVariable String addressBookName) {

        List<Contact> contacts = service.getContacts(addressBookName);

        return ResponseEntity.ok(contacts);
    }
}