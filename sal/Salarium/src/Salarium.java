/*
 * Main script to run
 * 
 * TBA
 * Retain Themes Information
 * Window Management Control 
 * Remote Server Availability (bayaran atay)
 * [done] Ang email ra need para mag forget password 
 * [done] Ang update nga calculation ichange (Edit Record both Expense and Income)
 * [done] Ang delete all data inaog in place sa logout nga disabled
 * [done] Mag integrate ug referential ID sa akong databases
 * [done] Float ang atong gamiton for all numbers
 * [done] Boolean ang gamiton sa login state
 * [done] Set Red Text if the cash balance is less than zero 
 * [done] mag tarong sa sort sa tables in home 
 * [done] mag fix sa full overview data
 */

import java.sql.*;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

//import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

public class Salarium {
    static CheckConnection postgres = new CheckConnection();

    static boolean loginState; 
    static int user_id;
    static String themeGrabbed;


    public Salarium(){
        autoLogin();
    }

    // Grab a user_id in the statement on which its login_state is online or value on 1
    public static void autoLogin (){
            try (Connection connection = DriverManager.getConnection(postgres.DB_URL, postgres.USER, postgres.PASSWORD)) {
                String query = """
                                SELECT * FROM public.login_history
                                ORDER BY login_id DESC, login_date DESC, login_time DESC
                                """;
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    ResultSet resultSet = statement.executeQuery();
                        if (resultSet.next()) {
    
                            // Retrieve data from the result set        
                            user_id = resultSet.getInt("id");
                            boolean loginState = resultSet.getBoolean("login_state");
    
                            if (loginState){     
                                System.out.println("Auto login user id retrieved: " + user_id + "\n");
                                themeGenerator(user_id);
                                new Home().setVisible(true);
                            } else {                
                                System.out.println("Auto login failed no user id that is logged retrieved\n");
                                new Login().setVisible(true);
                            }   
    
                        } else {
                            new Login().setVisible(true);
                        }
                    
                } catch (SQLException ex) {
                    System.err.println("Error: Something went wrong connecting executing query\n");
                    ex.printStackTrace();
                }

            } catch (SQLException ex) {
                System.err.println("Error: Unable to connect to the database.\n");
                ex.printStackTrace();
            }
    }

    public static void themeGenerator(int idd){
        try (Connection connection = DriverManager.getConnection(postgres.DB_URL, postgres.USER, postgres.PASSWORD)) {
            String query = "SELECT pref_themes FROM public.client_users WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)){
                statement.setInt(1, idd);
                ResultSet result = statement.executeQuery();
                if (result.next()) {
                    themeGrabbed = result.getString("pref_themes");

                    if (themeGrabbed != null) {
                        new Home().changeTheme(themeGrabbed);
                    }

                    System.out.println(themeGrabbed);
                    System.out.println("Theme preference updated successfully.");
                    
                } else {
                    System.out.println("No rows affected. User not found?");
                }

            } catch (SQLException e) {
                System.err.println("Error updating theme preference in the database.");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.err.println("Error connecting to the database.");
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {

        // Themes look and feel
        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());

        } catch( UnsupportedLookAndFeelException ex ) {
            System.err.println( "Failed to initialize LaF" );
        }


        SwingUtilities.invokeLater(() -> {
            CreateTables.tables();
            new Salarium();
           });

    }

}
