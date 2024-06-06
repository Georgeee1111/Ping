
import java.sql.*;

import javax.swing.JOptionPane;

import java.awt.event.*;
public class PasswordRecovery extends javax.swing.JPanel {
    static CheckConnection postgres = new CheckConnection();

    public PasswordRecovery() {
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {
        okButton = new javax.swing.JButton();
        prLabel = new javax.swing.JLabel();
        email = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();

        setLayout(null);
        prLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); 
        prLabel.setForeground(new java.awt.Color(0, 165, 230));
        prLabel.setText("Password Recovery");
        add(prLabel);
        prLabel.setBounds(75, 18, 200, 23);

        email.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); 
        email.setText("Username / Email: ");
        add(email);
        email.setBounds(10, 65, 150, 15);

        emailField.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); 
        add(emailField);
        emailField.setBounds(120, 60, 199, 25);


        okButton.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); 
        okButton.setBackground(new java.awt.Color(0, 165, 230));
        okButton.setForeground(new java.awt.Color(255, 255, 255));
        okButton.setText("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                    okButtonAction(evt);
            }
        });

        add(okButton);
        okButton.setBounds(260, 100, 61, 23);


    }// </editor-fold>                        

    // Methods
    public void okButtonAction(ActionEvent evt){
        String emailF = emailField.getText();
        
        if (emailF == null){
            JOptionPane.showMessageDialog(null, "Please fill in the empty field.");
        } else {
            try (Connection connection = DriverManager.getConnection(postgres.DB_URL, postgres.USER, postgres.PASSWORD)){
                String query = "SELECT * FROM public.client_users WHERE email = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, emailField.getText());

                ResultSet fetch = statement.executeQuery();

                if (fetch.next()) {
                    String email = fetch.getString("email");
                    String pword = fetch.getString("password");

                    if (email.equals(emailField.getText())){
                        String info = "Email:  " + email + "\n" + "Password:  " + pword;
                        JOptionPane.showMessageDialog(null, info, "Your Credentials", JOptionPane.INFORMATION_MESSAGE);
                        Login.passwordReco.dispose();
                        Login.enableForgBtn();
                        Login.loginButton.setEnabled(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Your username / email didn't find any in our database.");
                    }



                } else {
                    JOptionPane.showMessageDialog(null, "Your username / email didn't find any in our database.");
                }

            } catch (SQLException e){
                e.printStackTrace();
            }
   
        }



    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton okButton;
    private javax.swing.JLabel prLabel;
    private javax.swing.JLabel email;
    private javax.swing.JTextField emailField;
    // End of variables declaration
}
