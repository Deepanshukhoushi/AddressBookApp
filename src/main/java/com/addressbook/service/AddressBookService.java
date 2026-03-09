package com.addressbook.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.addressbook.dto.AddressBookDTO;
import com.addressbook.model.Contact;

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

		List<Contact> contactList = addressBookMap.get(bookName);

		String name = dto.getFirstName() + " " + dto.getLastName();

		boolean duplicateExists = contactList.stream()
				.anyMatch(contact -> contact.getFirstName().equalsIgnoreCase(dto.getFirstName())
						&& contact.getLastName().equalsIgnoreCase(dto.getLastName()));

		if (duplicateExists) {

			log.warn("Duplicate entry detected for: {}", name);

			throw new IllegalArgumentException("Contact already exists: " + name);
		}

		Contact contact = new Contact(dto.getFirstName(), dto.getLastName(), dto.getAddress(), dto.getCity(),
				dto.getState(), dto.getZip(), dto.getPhoneNumber(), dto.getEmail());

		contactList.add(contact);

		log.info("Contact added to {} AddressBook. Total contacts: {}", bookName, contactList.size());

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

		return contactList.removeIf(contact -> contact.getFirstName().equalsIgnoreCase(firstName));
	}

	public List<Contact> searchByCity(String city) {

		log.info("Searching for persons in city: {}", city);

		List<Contact> results = addressBookMap.values().stream().flatMap(List::stream)
				.filter(contact -> contact.getCity().equalsIgnoreCase(city)).toList();

		if (results.isEmpty()) {
			throw new IllegalArgumentException("No contacts found in city: " + city);
		}

		return results;
	}

	public List<Contact> searchByState(String state) {

		log.info("Searching for persons in state: {}", state);

		List<Contact> results = addressBookMap.values().stream().flatMap(List::stream)
				.filter(contact -> contact.getState().equalsIgnoreCase(state)).toList();

		if (results.isEmpty()) {
			throw new IllegalArgumentException("No contacts found in state: " + state);
		}

		return results;
	}

	public Map<String, List<Contact>> viewByCity() {

		log.info("Generating view grouped by City");

		Map<String, List<Contact>> cityDictionary = addressBookMap.values().stream().flatMap(List::stream)
				.collect(Collectors.groupingBy(Contact::getCity));

		if (cityDictionary.isEmpty()) {
			throw new IllegalArgumentException("No contacts available to group by city");
		}

		return cityDictionary;
	}

	public Map<String, List<Contact>> viewByState() {

		log.info("Generating view grouped by State");

		Map<String, List<Contact>> stateDictionary = addressBookMap.values().stream().flatMap(List::stream)
				.collect(Collectors.groupingBy(Contact::getState));

		if (stateDictionary.isEmpty()) {
			throw new IllegalArgumentException("No contacts available to group by state");
		}

		return stateDictionary;
	}

	// Count contacts in the given city across all address books
	public long countByCity(String city) {

		log.info("Counting contacts in city: {}", city);

		return addressBookMap.values().stream().flatMap(List::stream)
				.filter(contact -> contact.getCity().equalsIgnoreCase(city)).count();
	}

	// Count contacts in the given state across all address books
	public long countByState(String state) {

		log.info("Counting contacts in state: {}", state);

		return addressBookMap.values().stream().flatMap(List::stream)
				.filter(contact -> contact.getState().equalsIgnoreCase(state)).count();
	}

	// Get contacts from specific AddressBook
	public List<Contact> getContacts(String bookName) {

		return addressBookMap.getOrDefault(bookName, new ArrayList<>());
	}
	
	// Sort all contacts alphabetically by first name across all address books
	public List<Contact> sortContactsByName() {

	    log.info("Sorting contacts alphabetically by name");

	    return addressBookMap.values()
	            .stream()
	            .flatMap(List::stream)
	            .sorted(Comparator.comparing(Contact::getFirstName))
	            .toList();
	}
}