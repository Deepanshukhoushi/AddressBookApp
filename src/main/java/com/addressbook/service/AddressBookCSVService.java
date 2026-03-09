package com.addressbook.service;

import com.addressbook.model.Contact;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

public class AddressBookCSVService {

    private static final String FILE_PATH = "addressbook.csv";

    // Write contacts to CSV file
    public void writeContactsToCSV(List<Contact> contacts) {

        try (FileWriter writer = new FileWriter(FILE_PATH)) {

            StatefulBeanToCsv<Contact> beanToCsv =
                    new StatefulBeanToCsvBuilder<Contact>(writer)
                            .withApplyQuotesToAll(false)
                            .build();

            beanToCsv.write(contacts);

            System.out.println("Contacts written to CSV successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Read contacts from CSV file
    public List<Contact> readContactsFromCSV() {

        try (FileReader reader = new FileReader(FILE_PATH)) {

            CsvToBean<Contact> csvToBean =
                    new CsvToBeanBuilder<Contact>(reader)
                            .withType(Contact.class)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();

            List<Contact> contacts = csvToBean.parse();

            return contacts;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}