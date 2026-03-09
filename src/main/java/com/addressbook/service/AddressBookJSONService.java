package com.addressbook.service;

import com.addressbook.model.Contact;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.List;

public class AddressBookJSONService {

    private static final String FILE_PATH = "addressbook.json";

    private Gson gson = new Gson();

    // Write contacts to JSON file
    public void writeContactsToJSON(List<Contact> contacts) {

        try (FileWriter writer = new FileWriter(FILE_PATH)) {

            gson.toJson(contacts, writer);

            System.out.println("Contacts written to JSON successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Read contacts from JSON file
    public List<Contact> readContactsFromJSON() {

        try (FileReader reader = new FileReader(FILE_PATH)) {

            Type contactListType = new TypeToken<List<Contact>>() {}.getType();

            return gson.fromJson(reader, contactListType);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}