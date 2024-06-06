import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.sql.*;
import javax.swing.*;

public class Registration extends JFrame {
    static CheckConnection postgres = new CheckConnection();
    Connection connection = null;

    // Class Attributes or Global Var para ma access sa methods sa ubus
    JTextField fnameTextField;
    JTextField lnameTextField;
    JTextField emailTextField;
    JTextField pwordTextField;
    JCheckBox termsandconditionsCheckbox;

    // Constructor | Methods Intializer
    public Registration() {
        initComponents();

    }

    public void initComponents() {

        // Method Local Variables
        JLabel registerLabel = new JLabel();
        JLabel fnameLabel = new JLabel();
        JLabel lnameLabel = new JLabel();
        JLabel emailLabel = new JLabel();
        JLabel pwordLabel = new JLabel();
        JButton tandConButton = new JButton();
        JButton registerBtn = new JButton();

        // Panel
        JPanel registerPanel = new JPanel();


        // Frame Properties
        setTitle("Register");
        setResizable(false);

        setAlwaysOnTop(true);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                Login.enableSignBtn();
                Login.enableForgBtn();
                Login.loginButton.setEnabled(true);
            }
        });



        // Frame Widgets
        registerLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 25));
        registerLabel.setForeground(new Color(41, 173, 178));
        registerLabel.setText("Sign Up Form");

        fnameLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
        fnameLabel.setForeground(new Color(8, 83, 95));
        fnameLabel.setText("First Name");

        fnameTextField = new JTextField();
        fnameTextField.setBorder(BorderFactory.createLineBorder(new Color(153, 153, 153)));
        fnameTextField.setToolTipText("Type your first name");
        fnameTextField.setCursor(new Cursor(Cursor.HAND_CURSOR));

        lnameLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
        lnameLabel.setForeground(new Color(8, 83, 95));
        lnameLabel.setText("Last Name");

        lnameTextField = new JTextField();
        lnameTextField.setBorder(BorderFactory.createLineBorder(new Color(153, 153, 153)));
        lnameTextField.setToolTipText("Type your last name");
        lnameTextField.setCursor(new Cursor(Cursor.HAND_CURSOR));

        emailLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
        emailLabel.setForeground(new Color(8, 83, 95));
        emailLabel.setText("Username or Email");

        emailTextField = new JTextField();
        emailTextField.setBorder(BorderFactory.createLineBorder(new Color(153, 153, 153)));
        emailTextField.setToolTipText("Enter your username");
        emailTextField.setCursor(new Cursor(Cursor.HAND_CURSOR));

        pwordLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
        pwordLabel.setForeground(new Color(8, 83, 95));
        pwordLabel.setText("Password");

        pwordTextField = new JTextField();
        pwordTextField.setBorder(BorderFactory.createLineBorder(new Color(153, 153, 153)));
        pwordTextField.setToolTipText("Enter your password");
        pwordTextField.setCursor(new Cursor(Cursor.HAND_CURSOR));

        termsandconditionsCheckbox = new JCheckBox();
        termsandconditionsCheckbox.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 9)); 
        termsandconditionsCheckbox.setText("I have read, understood, and agreed to the");
        termsandconditionsCheckbox.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        tandConButton.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN,9)); 
        tandConButton.setText("Terms and Conditions");
        tandConButton.setBorder(null);
        tandConButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        /* To be added window of Terms and Conditions 

        tandConButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tandConButtonActionPerformed(evt);
            }
        });

        */

        registerBtn.setBackground(new Color(5, 191, 219));
        registerBtn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11)); 
        registerBtn.setForeground(new Color(255, 255, 255));
        registerBtn.setText("Register");
        registerBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        registerBtn.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                register();
                Login.enableSignBtn();
            }

        });
        // <editor-fold defaultstate="collapsed" desc="DO NOT MODIFY - GROUP LAYOUT MANAGER">
        // Main Panel Layout Manager
        GroupLayout registerPanelLayout = new GroupLayout(registerPanel);
        registerPanel.setLayout(registerPanelLayout);
        registerPanelLayout.setHorizontalGroup(
                registerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(registerPanelLayout.createSequentialGroup()
                                .addGroup(registerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(registerPanelLayout.createSequentialGroup()
                                                .addGap(16, 16, 16)
                                                .addGroup(registerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(registerBtn, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(termsandconditionsCheckbox)
                                                        .addGroup(registerPanelLayout.createSequentialGroup()
                                                                .addGap(53, 53, 53)
                                                                .addComponent(tandConButton))
                                                        .addGroup(registerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(pwordLabel)
                                                                .addComponent(emailLabel)
                                                                .addComponent(lnameLabel)
                                                                .addComponent(fnameLabel)
                                                                .addComponent(lnameTextField)
                                                                .addComponent(fnameTextField)
                                                                .addComponent(emailTextField)
                                                                .addComponent(pwordTextField, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(registerPanelLayout.createSequentialGroup()
                                                .addGap(37, 37, 37)
                                                .addComponent(registerLabel)))
                                .addContainerGap(16, Short.MAX_VALUE))
        );
        registerPanelLayout.setVerticalGroup(
                registerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(registerPanelLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(registerLabel)
                                .addGap(18, 18, 18)
                                .addComponent(fnameLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fnameTextField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lnameLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lnameTextField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(emailLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(emailTextField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pwordLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pwordTextField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(termsandconditionsCheckbox)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tandConButton)
                                .addGap(13, 13, 13)
                                .addComponent(registerBtn, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(16, Short.MAX_VALUE))
        );

        // Frame Layout Manager
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(registerPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(registerPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );

        // Frame Properties - cont
        pack();
        setLocationRelativeTo(null); // Intentional to be placed here
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        // </editor-fold>
    }
    // Methods

     // Save Button
    public void register() {
        // Get Value from the Form Fields and Combo Boxes
        String firstName = fnameTextField.getText();
        String lastName = lnameTextField.getText();
        String email = emailTextField.getText();
        String password = pwordTextField.getText();
        Boolean ticked = termsandconditionsCheckbox.isSelected();
    
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in the empty fields", "Error", JOptionPane.ERROR_MESSAGE);

        } else {
            if (ticked.booleanValue()) {
                try (Connection connection = DriverManager.getConnection(postgres.DB_URL, postgres.USER, postgres.PASSWORD)) {

                    // Check if the email already exists (Email in PostgreSQL is set to UNIQUE) which means no duplication of email upon registry
                    if (isEmailAlreadyRegistered(connection, email)) {
                        JOptionPane.showMessageDialog(this, "Email already registered", "Error", JOptionPane.ERROR_MESSAGE);

                    } else {
                        // If email is not duplicate, proceed with registration
                        String query = "INSERT INTO client_users (fname, lname, email, password, money_balance) VALUES (?, ?, ?, ?, ?)";

                        try (PreparedStatement statement = connection.prepareStatement(query)) {
                            statement.setString(1, firstName);
                            statement.setString(2, lastName);
                            statement.setString(3, email);
                            statement.setString(4, password);
                            statement.setBigDecimal(5, new BigDecimal(0));
    
                            int resultSet = statement.executeUpdate();
    
                            if (resultSet > 0) {
                                // Both user id will increment - Same USER ID value
                                JOptionPane.showMessageDialog(this, "Registered Successfully!");
                                dispose();
                                Login.loginButton.setEnabled(true);
                                Login.enableForgBtn();
                            } else {
                                JOptionPane.showMessageDialog(this, "Registration failed", "Error", JOptionPane.ERROR_MESSAGE);
                                Login.loginButton.setEnabled(true);
                                Login.enableForgBtn();
                            }
                        }
                    }

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
            } else {
                JOptionPane.showMessageDialog(this, "Please agree to our terms and condition first before using our program", "Notice", JOptionPane.ERROR_MESSAGE);
            }
            //setBal();
        }
        
    }

    // Email Checker Method
    public boolean isEmailAlreadyRegistered(Connection connection, String email) throws SQLException {
        String query = "SELECT COUNT(*) FROM public.client_users WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0; // If count is greater than 0, email already exists
                }
            }
        }
        return false;

    }

}
