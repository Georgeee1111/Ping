
import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Objects;

public class Login extends JFrame {
    static CheckConnection postgres = new CheckConnection();

    // Class Global Variables / Attributes /
    static JButton forgetPassword;
    static JButton loginButton;
    static JButton signUpButton;
    static JButton confirmButton;
    static JFrame passwordReco;
    static JTextField emailField;
    static JPasswordField passwordField;
    static int user_id;
    static String username;

    // Constructor | Methods Intializer
    public Login() {
        initComponents();

    }


    public void initComponents() {
        // Class Global Variables Re-innit
        loginButton = new JButton();
        emailField = new JTextField();
        passwordField = new JPasswordField();
        forgetPassword = new JButton();
        signUpButton = new JButton();

        // Method Local Variables
        JLabel logoLabel = new JLabel();
        JLabel motoLabel1 = new JLabel();
        JLabel welcomeLabel = new JLabel();
        JLabel pleaseLoginLabel = new JLabel();
        JLabel emailLabel = new JLabel();
        JLabel passwordLabel = new JLabel();
        JCheckBox showPassCheckBox = new JCheckBox();
        JLabel copyRightJLabel = new JLabel();
        // Panel
        JPanel panelLogo = new JPanel();
        JPanel panelLogin = new JPanel();


        // Frame Properties
        setTitle("Budget Tracker: Login");
        setResizable(false);

        // Window Close Listener Handler
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to leave?",
                        "Confirmation", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });

        // Frame Widgets
        logoLabel.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/salarium.png"))));
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        motoLabel1.setBackground(new Color(255, 255, 255));
        motoLabel1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14)); 
        motoLabel1.setForeground(new Color(8, 83, 95));
        motoLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        motoLabel1.setText("Your Budget Tracking Partner");

        welcomeLabel.setBackground(new Color(255, 255, 255));
        welcomeLabel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 24)); 
        welcomeLabel.setForeground(new Color(8, 83, 95));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setText("Welcome");

        pleaseLoginLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12)); 
        pleaseLoginLabel.setForeground(new Color(8, 83, 95));
        pleaseLoginLabel.setText("Please Login");

        emailLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12)); 
        emailLabel.setForeground(new Color(8, 83, 95));
        emailLabel.setText("Username or Email");

        emailField.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12)); 
        emailField.setToolTipText("Enter your username or email");
        emailField.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        passwordLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12)); 
        passwordLabel.setForeground(new Color(8, 83, 95));
        passwordLabel.setText("Password");
        
        passwordField.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12)); 
        passwordField.setToolTipText("Enter your password");
        passwordField.setCursor(new Cursor(Cursor.HAND_CURSOR));

        forgetPassword.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 10)); 
        forgetPassword.setText("Forgot Password");
        forgetPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
        forgetPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                forgetPassButton(evt);
                disableForgBtn();
            }
        });

        loginButton.setBackground(new Color(5, 191, 219));
        loginButton.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12)); 
        loginButton.setForeground(new Color(255, 255, 255));
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.setText("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                loginButtonEvent(evt);
            }
        });

        signUpButton.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 10));
        signUpButton.setText("Sign Up");
        signUpButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                signUpButtonEvent(evt);
            }
        });

        showPassCheckBox.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 10)); 
        showPassCheckBox.setText("Show");
        showPassCheckBox.setCursor(new Cursor(Cursor.HAND_CURSOR));
        showPassCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                checkBox(evt);
            }
        });

        copyRightJLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 10));
        copyRightJLabel.setForeground(new Color(80, 80, 80));
        copyRightJLabel.setHorizontalAlignment(SwingConstants.CENTER);
        copyRightJLabel.setText("© 2023 LoginForm LTD. ALL RIGHTS RESERVED");


        // <editor-fold defaultstate="collapsed" desc="DO NOT MODIFY - GROUP LAYOUT MANAGER">
        // Panel Layout Manager - Login Credentials
        GroupLayout panelLoginLayout = new GroupLayout(panelLogin);
        panelLogin.setLayout(panelLoginLayout);
        panelLoginLayout.setHorizontalGroup(
                panelLoginLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, panelLoginLayout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelLoginLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(GroupLayout.Alignment.TRAILING, panelLoginLayout.createSequentialGroup()
                                                .addComponent(pleaseLoginLabel)
                                                .addGap(102, 102, 102))
                                        .addGroup(GroupLayout.Alignment.TRAILING, panelLoginLayout.createSequentialGroup()
                                                .addComponent(welcomeLabel, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
                                                .addGap(64, 64, 64))))
                        .addGroup(panelLoginLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(panelLoginLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(signUpButton)
                                        .addComponent(emailLabel)
                                        .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(panelLoginLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(panelLoginLayout.createSequentialGroup()
                                                        .addComponent(passwordLabel)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(forgetPassword, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
                                                .addGroup(GroupLayout.Alignment.LEADING, panelLoginLayout.createSequentialGroup()
                                                        .addComponent(showPassCheckBox)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))
                                                .addComponent(emailField, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(25, Short.MAX_VALUE))
        );
        panelLoginLayout.setVerticalGroup(
                panelLoginLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(panelLoginLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(welcomeLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pleaseLoginLabel)
                                .addGap(9, 9, 9)
                                .addComponent(emailLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(emailField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addGroup(panelLoginLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(passwordLabel)
                                        .addComponent(forgetPassword))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelLoginLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(loginButton)
                                        .addComponent(showPassCheckBox))
                                .addGap(18, 18, 18)
                                .addComponent(signUpButton)
                                .addContainerGap(20, Short.MAX_VALUE))
        );

        // Panel Layout Manager - Logo
        GroupLayout panelLogoLayout = new GroupLayout(panelLogo);
        panelLogo.setLayout(panelLogoLayout);

        panelLogoLayout.setHorizontalGroup(
                panelLogoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, panelLogoLayout.createSequentialGroup()
                                .addContainerGap(87, Short.MAX_VALUE)
                                .addComponent(motoLabel1, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54))
                        .addComponent(logoLabel, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        panelLogoLayout.setVerticalGroup(
                panelLogoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(panelLogoLayout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(logoLabel, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(motoLabel1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        // Frame Layout Manager
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(panelLogo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(copyRightJLabel, GroupLayout.PREFERRED_SIZE, 657, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(panelLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(panelLogo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(copyRightJLabel)
                                .addContainerGap())
        );

        // Frame Properties - cont
        pack();
        setLocationRelativeTo(null);// Intentional to be placed here
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        // </editor-fold>

    }
        
        

    // Methods

    // Window Management
    public static void disableSignBtn(){
        signUpButton.setEnabled(false);
    }
    public static void enableSignBtn(){
        signUpButton.setEnabled(true);
    }
    public static void disableConfBtn(){
        confirmButton.setEnabled(false);
    }
    public static void enableConfBtn(){
        confirmButton.setEnabled(true);
    }
    public static void disableForgBtn(){
        forgetPassword.setEnabled(false);
    }
    public static void enableForgBtn(){
        forgetPassword.setEnabled(true);
    }
    
    // forget password button - this one is coded in
    public void forgetPassButton(ActionEvent evt){
        loginButton.setEnabled(false);
        passwordReco = new JFrame("Forgot Password");
        passwordReco.setVisible(true);
        passwordReco.setResizable(false);
        passwordReco.setSize(350, 170);
        passwordReco.setLocationRelativeTo(forgetPassword);
        passwordReco.setContentPane(new PasswordRecovery());
        passwordReco.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        passwordReco.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                enableForgBtn();
                Login.loginButton.setEnabled(true);
            }
        });
    }

    public void loginButtonEvent(ActionEvent evt){
        performLogin();
    }

    public void signUpButtonEvent(ActionEvent evt){
        new Registration();
        loginButton.setEnabled(false);
        disableSignBtn();
        disableForgBtn();
    }
    
    public void checkBox(ActionEvent evt){
        JCheckBox checkBox = (JCheckBox) evt.getSource();
        passwordField.setEchoChar(checkBox.isSelected() ? 0 : '*');
    }

    // Checks if the credentials matches to the Database
    public boolean validateLogin(String username, String password) {
        try (Connection connection = DriverManager.getConnection(postgres.DB_URL, postgres.USER, postgres.PASSWORD)) {

            String query = "SELECT * FROM public.client_users WHERE email=? AND password=?";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, username);
                statement.setString(2, password);

                ResultSet resultSet = statement.executeQuery();

                return resultSet.next();
                
            }
            
        } catch (SQLException e)  {
            System.err.println("Error: Something went wrong connecting executing query\n");
            e.printStackTrace();
            return false;
        }

    }

    public void performLogin() {
        // Defaults incase if database is down
        String defaultUser = "login";
        String defaultPassword = "123";
        username = emailField.getText(); // grabbing the textfield
        char[] passwordChars = passwordField.getPassword(); // Password Field you need to convert this then to String 
        String password = new String(passwordChars);
        boolean state = true;

        // Validate login
        if (username.isEmpty() || password.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please fill in the empty field!");
        } else {
            if (username.equals(defaultUser) && password.equals(defaultPassword) || validateLogin(username, password)){
                // Update the login state every login
                try (Connection connection = DriverManager.getConnection(postgres.DB_URL, postgres.USER, postgres.PASSWORD)) {

                    String query = "INSERT INTO public.login_history (id, login_state) VALUES (?, ?)";
                    try (PreparedStatement statement = connection.prepareStatement(query)) {
                        statement.setInt(1, userIDVALUE());
                        statement.setBoolean(2, state);
                        int confirmation = statement.executeUpdate();

                        if (confirmation > 0){
                            // Change theme based on the data that was fetched 
                            String queryTheme = "SELECT pref_themes FROM public.client_users WHERE id = ?";
                            PreparedStatement statementTheme = connection.prepareStatement(queryTheme);
                                statementTheme.setInt(1, userIDVALUE());
                                ResultSet result = statementTheme.executeQuery();
                                if (result.next()) {
                                    String loginTheme = result.getString("pref_themes");
                                    if (loginTheme != null){
                                        Home instance = new Home();
                                        instance.changeTheme(loginTheme);
                                    }
                                    System.out.println("Login Theme set.");
                                } else {
                                    System.out.println("No rows affected. User not found?");
                                }
                
                            System.out.println("Successfully Logged In! User_ID: " + userIDVALUE());
                        } else {
                            System.out.println("Something is wrong");
                        }


                    }
                    
                } catch (SQLException e)  {
                    e.printStackTrace();
                }

            new Home().setVisible(true);
            setVisible(false);
            // Clear fields after login attempt
            emailField.setText("");
            passwordField.setText("");

            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password", "Wrong Credentials", JOptionPane.ERROR_MESSAGE);
            }
        }

    }
    // Store USER ID to retain information and filter information will be shown in the JTABLE
    public static int userIDVALUE(){
        try (Connection connection = DriverManager.getConnection(postgres.DB_URL, postgres.USER, postgres.PASSWORD)) {
            String queryGrabID = "SELECT id FROM public.client_users WHERE email = ? ";
            try (PreparedStatement statement = connection.prepareStatement(queryGrabID)) {
                statement.setString(1, username);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) { // if user_id was retrieve by doing log in
                    user_id = resultSet.getInt("id");
                } else {                // if use was already logged in and was directly proceed to the home page
                    user_id = Salarium.user_id;
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return user_id;
    }


}
