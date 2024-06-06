import java.sql.*;
public class CreateTables {
    static CheckConnection postgres = new CheckConnection();
    static Connection connection = null;

    public static void tables(){
        try(Connection connection = DriverManager.getConnection(postgres.DB_URL, postgres.USER, postgres.PASSWORD)){

        String query = """
            CREATE TABLE IF NOT EXISTS client_users (
                id SERIAL PRIMARY KEY,
                fname VARCHAR(255),
                lname VARCHAR(255),
                email VARCHAR(255) UNIQUE,
                password VARCHAR(255),
                pref_themes VARCHAR(30),
                money_balance NUMERIC
            );
                """;
        PreparedStatement statementq = connection.prepareStatement(query);
        statementq.execute();

        String query1 = """
            CREATE TABLE IF NOT EXISTS login_history (
                login_id SERIAL PRIMARY KEY,
                id INTEGER REFERENCES client_users(id),
                login_date date DEFAULT CURRENT_DATE, -- automatically filled out
                login_time timestamp DEFAULT CURRENT_TIMESTAMP, --automatically filled out
                login_state BOOLEAN
            );
                """;
        PreparedStatement statement = connection.prepareStatement(query1);
        statement.execute();

        // Pag Query sa dayun ug 500 after ma execute ni before gamita ang application
        String query2 = """
            CREATE TABLE IF NOT EXISTS transaction (
                transaction_no SERIAL PRIMARY KEY,
                transaction_id INTEGER,
                id INTEGER REFERENCES client_users(id),
                transaction_date DATE,
                transaction_type VARCHAR(255),
                transaction_category VARCHAR(255),
                transaction_notes TEXT,
                money_type VARCHAR(255),
                transaction_amount NUMERIC
            );
                """;
        PreparedStatement statement2 = connection.prepareStatement(query2);
        statement2.execute();

        } catch (SQLException e) {
            System.err.println("Something went wrong from creating tables");
            e.printStackTrace();
        } finally {
            // Closing the connection in a finally block to ensure it is always closed
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Connection closed successfully.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}