package com.addressbook.service;

import com.addressbook.model.Contact;

import java.io.*;
import java.util.List;

public class AddressBookFileIOService {

    private static final String FILE_PATH = "addressbook.txt";

    // Write contacts to a text file
    public void writeContactsToFile(List<Contact> contactList) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {

            for (Contact contact : contactList) {

                writer.write(contact.toString());
                writer.newLine();
            }

            System.out.println("Contacts successfully written to file.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Read contacts from the text file
    public void readContactsFromFile() {

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {

            String line;

            while ((line = reader.readLine()) != null) {

                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}