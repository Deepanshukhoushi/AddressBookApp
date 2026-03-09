# Address Book System 📖

## Project Overview
This project is a **Java Full Stack Case Study** focused on building a comprehensive Address Book System. It demonstrates the use of Spring Boot for microservices, Java Streams for data processing, and layered architecture principles.

## Tech Stack
* **Language:** Java 17
* **Framework:** Spring Boot
* **Build Tool:** Maven
* **Logging:** SLF4J / Logback
* **Version Control:** Git

---

## Use Case Implementation Tracker

### ✅ UC 1: Create Contact Structure
**Branch:** `feature/UC1-create-contact`

**Requirement:**
[cite_start]Ability to create a Contact in the Address Book with the following attributes[cite: 15, 16]:
* First Name
* Last Name
* Address
* City
* State
* Zip
* Phone Number
* Email

**Implementation Details:**
* [cite_start]Created a `Contact` Model in `com.addressbook.model` to hold person details[cite: 16, 20].
* Created an `AddressBookDTO` in `com.addressbook.dto` for secure data transfer.
* Initialized the `AddressBookApplication` and verified the project structure in the IDE.


🔗 **Code Link:**  
👉 [UC 1: Create Contact Structure](https://github.com/Deepanshukhoushi/AddressBookApp/tree/feature/UC1-create-contact)

---

### 🔄 UC 2: Add New Contact 
**Branch:** `feature/UC2-add-contact`

**Requirement:**
Ability to add a new Contact to the Address Book using a REST API or Console.

**Implementation Details:**
* **Controller Layer:** Created `AddressBookController` with a `@PostMapping` to handle incoming contact data.
* **Service Layer:** Created `AddressBookService` to manage the business logic of saving contacts.
* **DTO Integration:** Used `AddressBookDTO` to map request data to the system's internal model.

**Key Code Files:**
* 📁 [Controller: AddressBookController.java](src/main/java/com/addressbook/controller/AddressBookController.java)
* 📁 [Service: AddressBookService.java](src/main/java/com/addressbook/service/AddressBookService.java)
* 📁 [DTO: AddressBookDTO.java](src/main/java/com/addressbook/dto/AddressBookDTO.java)

🔗 **Code Link:**  
👉 [UC 2: Add New Contact](https://github.com/Deepanshukhoushi/AddressBookApp/tree/feature/UC2-add-contact)

---

### 🔄 UC 3: Edit Existing Contact 
**Branch:** `feature/UC3-edit-contact`

**Requirement:**
Ability to edit an existing Contact in the Address Book by searching for their **ID** using the REST API.

**Implementation Details:**
* **Controller Layer:** Added `@PutMapping` in `AddressBookController` to handle update requests for an existing contact.
* **Service Layer:** Implemented update logic in `AddressBookService` to search for the contact by ID and modify the existing contact details.
* **DTO Integration:** Used `AddressBookDTO` to receive the updated contact data and map it to the existing contact object.

**Key Code Files:**
* 📁 [Controller: AddressBookController.java](src/main/java/com/addressbook/controller/AddressBookController.java)
* 📁 [Service: AddressBookService.java](src/main/java/com/addressbook/service/AddressBookService.java)
* 📁 [DTO: AddressBookDTO.java](src/main/java/com/addressbook/dto/AddressBookDTO.java)

🔗 **Code Link:**  
👉 [UC 3: Edit Existing Contact](https://github.com/Deepanshukhoushi/AddressBookApp/tree/feature/UC3-edit-contact)

---

### 🔄 UC 4: Delete Existing Contact
**Branch:** `feature/UC4-delete-contact`

**Requirement:**
Ability to delete an existing Contact from the Address Book using their **ID** via the REST API.

**Implementation Details:**
* **Controller Layer:** Added `@DeleteMapping` in `AddressBookController` to handle deletion requests using a Path Variable (ID).
* **Service Layer:** Implemented logic in `AddressBookService` to remove the contact from the in-memory `ArrayList` using the provided ID/index.
* **Validation:** The service layer processes the removal and returns a success confirmation to the controller.

**Key Code Files:**
* 📁 [Controller: AddressBookController.java](src/main/java/com/addressbook/controller/AddressBookController.java)
* 📁 [Service: AddressBookService.java](src/main/java/com/addressbook/service/AddressBookService.java)

🔗 **Code Link:**  
👉 [UC 4: Delete Existing Contact](https://github.com/Deepanshukhoushi/AddressBookApp/tree/feature/UC4-delete-contact)

---

### 🔄 UC 5: Ability to Add Multiple Persons
**Branch:** `feature/UC5-add-multiple-contacts`

**Requirement:**
Ensure the system can handle adding multiple contact persons to the Address Book and maintain them in a collection.

**Implementation Details:**
* **Collection Framework:** Optimized the `ArrayList` usage in the Service Layer to handle an increasing number of contacts.
* **Service Layer:** Refactored the create logic to ensure new contacts are appended to the existing list without data loss.
* **GET Mapping:** Ensured the `@GetMapping` returns the full collection of contacts to verify multiple entries.

**Key Code Files:**
* 📁 [Controller: AddressBookController.java](src/main/java/com/addressbook/controller/AddressBookController.java)
* 📁 [Service: AddressBookService.java](src/main/java/com/addressbook/service/AddressBookService.java)

🔗 **Code Link:**  
👉 [UC 5: Ability to Add Multiple Persons](https://github.com/Deepanshukhoushi/AddressBookApp/tree/feature/UC5-add-multiple-contacts)

---

### 🔄 UC 6: Multiple Address Books
**Branch:** `feature/UC6-multiple-addressbooks`

**Requirement:**
Refactor the system to support multiple Address Books, each with a unique name, using a Dictionary (Map) structure.

**Implementation Details:**
* **Data Structure:** Replaced the single `List` with a `HashMap<String, List<Contact>>` to store multiple Address Books.
* **Controller Layer:** Updated endpoints to accept `addressBookName` as a path variable to target specific address books.
* **Service Layer:** Implemented logic to check for the existence of an Address Book before adding contacts.

**Key Code Files:**
* 📁 [Service: AddressBookService.java](src/main/java/com/addressbook/service/AddressBookService.java)
* 📁 [Controller: AddressBookController.java](src/main/java/com/addressbook/controller/AddressBookController.java)

🔗 **Code Link:**  
👉 [UC 6: Multiple Address Books](https://github.com/Deepanshukhoushi/AddressBookApp/tree/feature/UC6-multiple-addressbooks)

---

### 🔄 UC 7: Prevent Duplicate Entries
**Branch:** `feature/UC7-prevent-duplicate-contact`

**Requirement:**
Ensure there is no duplicate entry of the same person in a particular Address Book using Java Streams.

**Implementation Details:**
* **Logic:** Overrode the `equals()` and `hashCode()` methods in the `Contact` model to define equality based on name.
* **Java Streams:** Used `.stream().anyMatch()` to check if a contact already exists before adding a new one.
* **Validation:** Returns a warning or error response if a duplicate contact is detected.

**Key Code Files:**
* 📁 [Model: Contact.java](src/main/java/com/addressbook/model/Contact.java)
* 📁 [Service: AddressBookService.java](src/main/java/com/addressbook/service/AddressBookService.java)

🔗 **Code Link:**  
👉 [UC 7: Prevent Duplicate Entries](https://github.com/Deepanshukhoushi/AddressBookApp/tree/feature/UC7-prevent-duplicate-contact)

---

### 🔄 UC 8: Search Person by City or State
**Branch:** `feature/UC8-search-person-by-city-or-state`

**Requirement:**
Ability to search for a person across multiple address books by **City** or **State** using Java Streams.

**Implementation Details:**
* **Java Streams:** Utilized `flatMap` to consolidate contacts from all address books into a single stream.
* **Filtering:** Applied `.filter()` to find matches based on the city or state name (case-insensitive).
* **Controller:** Added global search endpoints `/search/city/{city}` and `/search/state/{state}`.

**Key Code Files:**
* 📁 [Service: AddressBookService.java](src/main/java/com/addressbook/service/AddressBookService.java)
* 📁 [Controller: AddressBookController.java](src/main/java/com/addressbook/controller/AddressBookController.java)

🔗 **Code Link:**  
👉 [UC 8: Search Person by City or State](https://github.com/Deepanshukhoushi/AddressBookApp/tree/feature/UC8-search-person-by-city-or-state)

---