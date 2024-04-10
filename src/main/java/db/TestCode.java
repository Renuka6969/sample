package db;

import java.sql.*;

public class TestCode {

	public static void main(String[] args) 
	throws Exception
	{	
		Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
				
		createCustomer(connection, "Bob", "Doe", "bob.doe@example.com", "123-457-7890", "123 Main St, Anytown, USA");
        //createCustomer(connection, "Jane", "Smith", "jane.smith@example.com", "987-654-3210", "456 Elm St, Anycity, USA");

        // Example of reading data
        System.out.println("All Customer:");
        readCustomer(connection);

        // Example of updating data
        updateCustomer(connection, 1, "John", "Doe", "john.doe@example.com", "999-999-9999", "123 Main St, Anytown, USA");

        // Example of deleting data
        deleteCustomer(connection, 2);
        
	}
	
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/emp";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";



    public static void createCustomer(Connection connection, String firstName, String lastName, String email, String phoneNumber, String address) throws SQLException {
        String sql = "INSERT INTO Customer (first_name, last_name, email, phone_number, address) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, email);
            statement.setString(4, phoneNumber);
            statement.setString(5, address);
            statement.executeUpdate();
        }
    }

    public static void readCustomer(Connection connection) throws SQLException {
        String sql = "SELECT * FROM Customer";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                System.out.println("Customer ID: " + resultSet.getInt("customer_id"));
                System.out.println("Name: " + resultSet.getString("first_name") + " " + resultSet.getString("last_name"));
                System.out.println("Email: " + resultSet.getString("email"));
                System.out.println("Phone Number: " + resultSet.getString("phone_number"));
                System.out.println("Address: " + resultSet.getString("address"));
                System.out.println("-----------------------------");
            }
        }
    }

    public static void updateCustomer(Connection connection, int customerId, String firstName, String lastName, String email, String phoneNumber, String address) throws SQLException {
        String sql = "UPDATE Customer SET first_name=?, last_name=?, email=?, phone_number=?, address=? WHERE customer_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, email);
            statement.setString(4, phoneNumber);
            statement.setString(5, address);
            statement.setInt(6, customerId);
            statement.executeUpdate();
        }
    }

    public static void deleteCustomer(Connection connection, int customerId) throws SQLException {
        String sql = "DELETE FROM Customer WHERE customer_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, customerId);
            statement.executeUpdate();
        }
    }


}
