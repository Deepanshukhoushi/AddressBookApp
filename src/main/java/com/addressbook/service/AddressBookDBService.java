package com.addressbook.service;

import com.addressbook.model.Contact;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressBookDBService {

	private static final String URL = "jdbc:mysql://localhost:3306/addressbook_db";
	private static final String USER = "root";
	private static final String PASSWORD = "Deepanshu";

	// Retrieve all contacts from database
	public List<Contact> getAllContactsFromDB() {

		List<Contact> contactList = new ArrayList<>();

		String query = "SELECT * FROM address_book_table";

		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(query)) {

			while (rs.next()) {

				Contact contact = new Contact(rs.getString("first_name"), rs.getString("last_name"),
						rs.getString("address"), rs.getString("city"), rs.getString("state"), rs.getString("zip"),
						rs.getString("phone_number"), rs.getString("email"));

				contactList.add(contact);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return contactList;
	}

	// Update contact city in DB using PreparedStatement
	public boolean updateContactCity(String firstName, String city) {

		String query = "UPDATE address_book_table SET city=? WHERE first_name=?";

		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement statement = connection.prepareStatement(query)) {

			statement.setString(1, city);
			statement.setString(2, firstName);

			int rowsUpdated = statement.executeUpdate();

			return rowsUpdated > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
}