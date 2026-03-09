package com.addressbook.controller;

import java.util.List;
import java.util.Map;

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

	// Create contact in specific AddressBook
	@PostMapping("/{addressBookName}/create")
	public ResponseEntity<Contact> createAddressBookData(@PathVariable String addressBookName,
			@RequestBody AddressBookDTO dto) {

		Contact contact = service.addContact(addressBookName, dto);

		return ResponseEntity.ok(contact);
	}

	// Update contact in specific AddressBook
	@PutMapping("/{addressBookName}/update/{firstName}")
	public ResponseEntity<Contact> updateAddressBookData(@PathVariable String addressBookName,
			@PathVariable String firstName, @RequestBody AddressBookDTO dto) {

		Contact updatedContact = service.updateContact(addressBookName, firstName, dto);

		if (updatedContact != null) {
			return ResponseEntity.ok(updatedContact);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// Delete contact from specific AddressBook
	@DeleteMapping("/{addressBookName}/delete/{firstName}")
	public ResponseEntity<String> deleteAddressBookData(@PathVariable String addressBookName,
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
	public ResponseEntity<List<Contact>> getContacts(@PathVariable String addressBookName) {

		List<Contact> contacts = service.getContacts(addressBookName);

		return ResponseEntity.ok(contacts);
	}

	@GetMapping("/search/city/{city}")
	public ResponseEntity<List<Contact>> searchByCity(@PathVariable String city) {

		List<Contact> contacts = service.searchByCity(city);

		return ResponseEntity.ok(contacts);
	}

	@GetMapping("/search/state/{state}")
	public ResponseEntity<List<Contact>> searchByState(@PathVariable String state) {

		List<Contact> contacts = service.searchByState(state);

		return ResponseEntity.ok(contacts);
	}

	@GetMapping("/view/city")
	public ResponseEntity<Map<String, List<Contact>>> viewByCity() {

		return ResponseEntity.ok(service.viewByCity());
	}

	@GetMapping("/view/state")
	public ResponseEntity<Map<String, List<Contact>>> viewByState() {

		return ResponseEntity.ok(service.viewByState());
	}

	// API to get the number of contacts in a specific city
	@GetMapping("/count/city/{city}")
	public ResponseEntity<Long> countByCity(@PathVariable String city) {

		long count = service.countByCity(city);

		return ResponseEntity.ok(count);
	}

	// API to get the number of contacts in a specific state
	@GetMapping("/count/state/{state}")
	public ResponseEntity<Long> countByState(@PathVariable String state) {

		long count = service.countByState(state);

		return ResponseEntity.ok(count);
	}
}