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

    public Connection getConnection() throws SQLException {
        try {
            // Check if the JDBC driver is available
            Class.forName("org.postgresql.Driver");
            System.out.println("JDBC driver loaded successfully.");
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL JDBC driver not found.", e);
        }

        // Establish and return a connection to the database
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        CheckConnection postgres = new CheckConnection();
        try (Connection connection = postgres.getConnection()) {
            System.out.println("Connection to the database established successfully.\n\n");
        } catch (SQLException e) {
            System.err.println("Error: Unable to connect to the database.");
            e.printStackTrace();
        }
    }
}
