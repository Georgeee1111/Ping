/*
 * WARNING !!!!!
 * 
 * This script will drop all your tables and reset all the data in your app
 * Run this with caution and only if you understand
 * 
 * you may need to refresh your database 
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DropTables {
    static CheckConnection postgres = new CheckConnection();
    static Connection connection = null;


    public DropTables(){
        try(Connection connection = DriverManager.getConnection(postgres.DB_URL, postgres.USER, postgres.PASSWORD)){
            
            String query = """
                DROP TABLE IF EXISTS public.login_history;
                    """;
            PreparedStatement statementh = connection.prepareStatement(query);
            statementh.execute();

            String query1 = """
                DROP TABLE IF EXISTS public.account_balance;
                    """;
            PreparedStatement statement = connection.prepareStatement(query1);
            statement.execute();
    
            // Pag Query sa dayun ug 500 after ma execute ni before gamita ang application
            String query2 = """
                DROP TABLE IF EXISTS public.transaction;
                    """;
            PreparedStatement statement2 = connection.prepareStatement(query2);
            statement2.execute();
    
            String query3 = """
                DROP TABLE IF EXISTS public.login;
                    """;
    
            PreparedStatement statement3 = connection.prepareStatement(query3);
            statement3.execute();
    
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
        new DropTables();
    }

}
