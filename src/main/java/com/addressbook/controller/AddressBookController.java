package com.addressbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.addressbook.dto.AddressBookDTO;
import com.addressbook.model.Contact;
import com.addressbook.service.AddressBookService;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {

    @Autowired
    private AddressBookService service;

    @PostMapping("/create")
    public ResponseEntity<Contact> createAddressBookData(@RequestBody AddressBookDTO dto) {

        Contact contact = service.addContact(dto);

        return ResponseEntity.ok(contact);
    }
    
    @PutMapping("/update/{firstName}")
    public ResponseEntity<Contact> updateAddressBookData(
            @PathVariable String firstName,
            @RequestBody AddressBookDTO dto) {

        Contact updatedContact = service.updateContact(firstName, dto);

        if (updatedContact != null) {
            return ResponseEntity.ok(updatedContact);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/delete/{firstName}")
    public ResponseEntity<String> deleteAddressBookData(@PathVariable String firstName) {

        boolean deleted = service.deleteContact(firstName);

        if (deleted) {
            return ResponseEntity.ok("Deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/get")
    public ResponseEntity<List<Contact>> getAllContacts() {

        List<Contact> contacts = service.getAllContacts();

        return ResponseEntity.ok(contacts);
    }
}

