/*
 * This script will check the connection of your localhost database (PostgreSQL)
 * 
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CheckConnection {
    String DB_URL;
    String USER;
    String PASSWORD;

    // Constructor and this will be accessed throughout the entire program
    public CheckConnection() {
        DB_URL = "jdbc:postgresql://localhost:5432/postgres";
        USER = "postgres";
        PASSWORD = "1234";

    }

    public static void main(String[] args) {
        CheckConnection postgres = new CheckConnection();
        try {
            // Check if the JDBC driver is available
            Class.forName("org.postgresql.Driver");
            System.out.println("JDBC driver loaded successfully.");

            // Establish a connection to the database
            try (Connection connection = DriverManager.getConnection(postgres.DB_URL, postgres.USER,
                    postgres.PASSWORD)) {
                System.out.println("Connection to the database established successfully.\n\n");

                // You can perform additional actions here if needed

            } catch (SQLException e) {
                System.err.println("Error: Unable to connect to the database.");
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            System.err.println("Error: PostgreSQL JDBC driver not found.");
            e.printStackTrace();
        }
    }

}
