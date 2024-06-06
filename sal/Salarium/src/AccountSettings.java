
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountSettings extends javax.swing.JFrame {
    static CheckConnection postgres = new CheckConnection();
    static String fname;
    static String lname;
    static String demail;

    public AccountSettings() {
        initComponents();
        loadInformation (); 
    }
                         
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        account_settings = new javax.swing.JLabel();
        delete_all_user_data = new javax.swing.JButton();
        profilePictureIcon = new javax.swing.JLabel();
        first_name = new javax.swing.JLabel();
        first_name_val = new javax.swing.JTextField();
        last_name = new javax.swing.JLabel();
        last_name_val = new javax.swing.JTextField();
        email = new javax.swing.JLabel();
        email_val = new javax.swing.JTextField();
        password = new javax.swing.JLabel();
        password_val = new javax.swing.JTextField();
        confirm_password = new javax.swing.JLabel();
        confirm_password_val = new javax.swing.JTextField();
        logout = new javax.swing.JButton();
        confirm_change_pass = new javax.swing.JButton();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("My Profile");
        setResizable(false);

        account_settings.setBackground(new java.awt.Color(8, 83, 95));
        account_settings.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24)); 
        account_settings.setForeground(new java.awt.Color(0, 165, 230));
        account_settings.setText("Account Settings");

        delete_all_user_data.setBackground(new java.awt.Color(102, 0, 0));
        delete_all_user_data.setVisible(false);
        delete_all_user_data.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 10)); 
        delete_all_user_data.setForeground(new java.awt.Color(255, 255, 255));
        delete_all_user_data.setText("DELETE ALL MY DATA");
        delete_all_user_data.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_all_user_dataActionPerformed(evt);
            }
        });

        profilePictureIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        profilePictureIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic.png"))); 
        profilePictureIcon.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        first_name.setBackground(new java.awt.Color(8, 83, 95));
        first_name.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); 
        first_name.setForeground(new java.awt.Color(0, 105, 150));
        first_name.setText("First Name");

        first_name_val.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); 
        
        last_name.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); 
        last_name.setForeground(new java.awt.Color(0, 105, 150));
        last_name.setText("Last Name");

        last_name_val.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); 
        

        email.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); 
        email.setForeground(new java.awt.Color(0, 105, 150));
        email.setText("Email");

        email_val.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); 
        email_val.setEnabled(false);
        email_val.setText("Disabled");

        password.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); 
        password.setForeground(new java.awt.Color(0, 105, 150));
        password.setText("New Password");

        password_val.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); 
        
        confirm_password.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); 
        confirm_password.setForeground(new java.awt.Color(0, 105, 150));
        confirm_password.setText("Confirm New Password");

        confirm_password_val.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); 
        
        //logout.setVisible(false);
        logout.setEnabled(false);
        logout.setBackground(new java.awt.Color(0, 165, 230));
        logout.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); 
        logout.setForeground(new java.awt.Color(255, 255, 255));
        logout.setText("Logout");
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });

        confirm_change_pass.setBackground(new java.awt.Color(5, 139, 93));
        confirm_change_pass.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); 
        confirm_change_pass.setForeground(new java.awt.Color(255, 255, 255));
        confirm_change_pass.setText("Change Password");
        confirm_change_pass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirm_change_passActionPerformed(evt);
            }
        });

        // <editor-fold defaultstate="collapsed" desc="DO NOT MODIFY - GROUP LAYOUT MANAGER">
        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(profilePictureIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(confirm_change_pass)
                        .addGap(140, 140, 140)
                        .addComponent(logout)
                        .addGap(20, 20, Short.MAX_VALUE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(account_settings)
                            .addComponent(last_name_val, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(last_name)
                            .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(mainPanelLayout.createSequentialGroup()
                                    .addComponent(first_name)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(delete_all_user_data))
                                .addComponent(first_name_val, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(email_val)
                                .addComponent(email)
                                .addComponent(password)
                                .addComponent(password_val)
                                .addComponent(confirm_password)
                                .addComponent(confirm_password_val, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(profilePictureIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(account_settings)
                .addGap(20, 20, 20)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(first_name)
                    .addComponent(delete_all_user_data))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(first_name_val, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(last_name)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(last_name_val, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(email)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(email_val, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(password)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(password_val, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(confirm_password)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(confirm_password_val, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirm_change_pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logout, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>                        

    private void confirm_change_passActionPerformed(java.awt.event.ActionEvent evt) {                                                    
        try (Connection connection = DriverManager.getConnection(postgres.DB_URL, postgres.USER, postgres.PASSWORD)) {
            String query = "UPDATE public.client_users SET password = ? WHERE id = ?";

            PreparedStatement statement = connection.prepareStatement(query);

            String pword = password_val.getText();
            String pwordCon = confirm_password_val.getText();

            if (pword.equals(pwordCon)){
                statement.setString(1, pword);
            } else {
                JOptionPane.showMessageDialog(null, "Please make sure that the password ");
            }

            statement.setInt(2, Login.userIDVALUE());

            int result = statement.executeUpdate();

            if (result > 0 ){
                JOptionPane.showMessageDialog(null, "Password Successfully Changed");
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Change Password Failed, Something is wrong");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle SQLException as needed
        }
    }                                                   



    // fill the textfields with the data of login table
    public void loadInformation(){
        try (Connection connection = DriverManager.getConnection(postgres.DB_URL, postgres.USER, postgres.PASSWORD)) {
            String query = "SELECT fname, lname, email, password FROM public.client_users WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, Login.userIDVALUE());

            ResultSet result = statement.executeQuery();

            if (result.next()){
                // Retrieved Data then store it to variable then assign it to the textfields
                fname = result.getString("fname");
                lname = result.getString("lname");
                demail = result.getString("email");

                first_name_val.setText(fname);
                last_name_val.setText(lname);
                email_val.setText(demail);

                System.out.println();
            }

        
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle SQLException as needed
        }

    }   

    // Logout
    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {    
                                           
        int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            new Home().setVisible(false);
            new Login().setVisible(true);
            try (Connection connection = DriverManager.getConnection(postgres.DB_URL, postgres.USER, postgres.PASSWORD)) {

                String query = "INSERT INTO public.login_history (user_id, login_state) VALUES (?, ?)";
    
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setInt(1, Login.userIDVALUE());
                    statement.setInt(2, 0);

                    int confirmation = statement.executeUpdate();

                    if (confirmation > 0){
                        System.out.println("Successfully Logged out! User_ID: " + Login.userIDVALUE());
                        // Clear the user_id para dli ma retain every mo run ang salarium
                        Salarium.user_id = 0;
                        Login.user_id = 0;
                        
                        dispose();
                      
                    } else {
                        System.out.println("Something is wrong, cannot log you out.");
                    }

                }
                
            } catch (SQLException e)  {
                e.printStackTrace();
            }

            
        }

    }                                      

    private void delete_all_user_dataActionPerformed(java.awt.event.ActionEvent evt) {
        int choice = JOptionPane.showConfirmDialog(null, "Do you really wanna delete all your data from this app?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {                                                 
            try(Connection connection = DriverManager.getConnection(postgres.DB_URL, postgres.USER, postgres.PASSWORD)){

                String query1 = """
                    UPDATE public.account_balance SET balance = ? WHERE user_id = ?
                        """;
                PreparedStatement statement = connection.prepareStatement(query1);
                statement.setInt(1, 0);
                statement.setInt(2, Login.userIDVALUE());
                int result = statement.executeUpdate();

                // Pag Query sa dayun ug 500 after ma execute ni before gamita ang application
                String query2 = """
                    DELETE FROM public.transaction WHERE user_id = ?;
                        """;
                PreparedStatement statement2 = connection.prepareStatement(query2);
                statement2.setInt(1, Login.userIDVALUE());
                int result2 = statement2.executeUpdate();

     

                if (result > 0 &&  result2 > 0 ){
                    Home.loadDataFromDatabase();
                    Home.retrieveBalance();
                    JOptionPane.showMessageDialog(null, "Successfully Deleted All Your Data");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Something went' wrong contact the developer!!");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        
        }
    }                                                    
                                           
    // Variables declaration - do not modify                     
    private javax.swing.JLabel account_settings;
    private javax.swing.JButton confirm_change_pass;
    private javax.swing.JLabel confirm_password;
    static javax.swing.JTextField confirm_password_val;
    private javax.swing.JButton delete_all_user_data;
    private javax.swing.JLabel email;
    static javax.swing.JTextField email_val;
    private javax.swing.JLabel first_name;
    static javax.swing.JTextField first_name_val;
    private javax.swing.JLabel last_name;
    static javax.swing.JTextField last_name_val;
    private javax.swing.JButton logout;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel password;
    static javax.swing.JTextField password_val;
    private javax.swing.JLabel profilePictureIcon;
    // End of variables declaration
    
}
