package com.addressbook.controller;

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

    @PostMapping("/create")
    public ResponseEntity<Contact> createAddressBookData(@RequestBody AddressBookDTO dto) {

        Contact contact = service.addContact(dto);

        return ResponseEntity.ok(contact);
    }
}