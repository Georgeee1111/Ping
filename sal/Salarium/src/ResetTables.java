/*
 * WARNING !!!!!
 * 
 * Do not run this unless you know what you are doing fcker
 * 
 * 
 * you may need to refresh your database 
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ResetTables {
    static CheckConnection postgres = new CheckConnection();
    static Connection connection = null;

    public static void tables(){
        try(Connection connection = DriverManager.getConnection(postgres.DB_URL, postgres.USER, postgres.PASSWORD)){
            String query1 = """
                DELETE FROM public.account_balance;
                    """;
            PreparedStatement statement = connection.prepareStatement(query1);
            statement.executeUpdate();

            String query = """
                DELETE FROM public.login_history;
                    )
                    """;
            PreparedStatement statementq = connection.prepareStatement(query);
            statementq.executeUpdate();


            // Pag Query sa dayun ug 500 after ma executeUpdate ni before gamita ang application
            String query2 = """
                DELETE FROM public.transaction;
                    """;
            PreparedStatement statement2 = connection.prepareStatement(query2);
            statement2.executeUpdate();

            String query3 = """
                DELETE FROM public.login;
                    """;

            PreparedStatement statement3 = connection.prepareStatement(query3);
            statement3.executeUpdate();

        } catch (SQLException e) {
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
    public static void main(String[] args) {
        tables();
    }
}
    
