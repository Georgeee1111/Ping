import java.awt.*;
import java.sql.*;
import java.text.*;
import javax.swing.*;
import java.util.Date;
import java.util.Random;
import java.awt.event.*;
import java.math.BigDecimal;

import com.raven.datechooser.DateChooser;




public class AddNewRecord extends JFrame {
    // SQL connection - Modify (localhost:2001/postgres) to your own info.
    static CheckConnection postgres = new CheckConnection();
    Connection connection = null;

    // Class Global Variables / Attributes
    JComboBox<Object> amountCB;
    JComboBox<Object> labelCB;
    JComboBox<Object> categoryCB;
    JComboBox<Object> paymentCB;
    JTextField dateSelectTextField;
    JTextField notesTField;
    static BigDecimal AmountVal, balance, result;

    // Constructor | Methods Intializer
    public AddNewRecord() {
        initComponents();
        Home.disableRecBtn();
    }


    public void initComponents() {
        // Class Global Variables Re-innit
        amountCB = new JComboBox<>();
        labelCB = new JComboBox<>();
        categoryCB = new JComboBox<>();
        paymentCB = new JComboBox<>();
        dateSelectTextField = new JTextField();
        notesTField = new JTextField();


        // Method Local Variables
        DateChooser calendar = new DateChooser();
        JLabel amountLabel = new JLabel();
        JButton saveButton = new JButton();
        JButton cancelButton = new JButton();
        JLabel labelLabel = new JLabel();
        JLabel categoryLabel = new JLabel();
        JLabel paymentType = new JLabel();
        JLabel dateLbl = new JLabel();
        JLabel notes = new JLabel();
        
        // Panel
        JPanel mainPanel = new JPanel();


        // Frame Properties
        setTitle("Add Record");
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                Home.enableRecBtn();
            }
        });

        // Window Close Listener Handler
        amountLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
        amountLabel.setForeground(new Color(8, 83, 95));
        amountLabel.setText("Amount");
        
        amountCB.setEditable(true);
        amountCB.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
        amountCB.setModel(new DefaultComboBoxModel<>(new String[] { "0", "200", "500", "1000", "2000",}));
        amountCB.setCursor(new Cursor(Cursor.HAND_CURSOR));

        labelLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
        labelLabel.setForeground(new Color(8, 83, 95));
        labelLabel.setText("Label");
        labelLabel.setHorizontalTextPosition(SwingConstants.CENTER);

        labelCB.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
        labelCB.setCursor(new Cursor(Cursor.HAND_CURSOR));
        labelCB.setModel(new DefaultComboBoxModel<>(new String[] {"EXPENSE", "INCOME"})); // Additional to code

        categoryLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
        categoryLabel.setForeground(new Color(8, 83, 95));
        categoryLabel.setText("Category");

        categoryCB.setEditable(true);
        categoryCB.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
        categoryCB.setModel(new DefaultComboBoxModel<>(new String[]
        { "Others", "Food and Drinks", "Bar, Cafe", "Groceries", "Fast-Food",
        "Shopping", "Clothes & Shoes", "Medicine", "Electronics",
        "Free Time", "Gifts, joy", "Health & beauty", "Home, garden", "Jewels, accessories", "Kids",
        "Pets, animals", "Tools", "Transportation", "Business Trips", "Long Distance", "Public Transport",
        "Life & Entertainment","Active sport, fitness", "Alcohol, tabacco", "Books, audio, and subscription",
        "Charity, gifts", "Culture, sport events", "Education, Development", "Health care, doctor", 
        "Hobbies", "Holiday, trips, hotels", "Life Events", "Lottery, casino", "Emotional Support", "Wellness, beauty",
        "Communication, PC", "Internet", "Phone, cell phone", "Postal service", "Software, apps, games", "Financial Expenses",
        "Advisory", "Charges, fees", "Child support", "Insurances", "Loan, interest",
        "Taxes" , "Bills"}
         ));
         categoryCB.setCursor(new Cursor(Cursor.HAND_CURSOR));


        paymentType.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
        paymentType.setForeground(new Color(8, 83, 95));
        paymentType.setText("Payment Type");
        paymentType.setCursor(new Cursor(Cursor.HAND_CURSOR));

        paymentCB.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
        paymentCB.setModel(new DefaultComboBoxModel<>(new String[] { "Cash", "Gcash", "Credit Card"}));
        paymentCB.setCursor(new Cursor(Cursor.HAND_CURSOR));

        dateLbl.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
        dateLbl.setForeground(new Color(8, 83, 95));
        dateLbl.setText("Date");

        dateSelectTextField.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
        dateSelectTextField.setHorizontalAlignment(JTextField.CENTER);
        dateSelectTextField.setCursor(new Cursor(Cursor.HAND_CURSOR));

        calendar.setForeground(new java.awt.Color(36, 36, 36));
        calendar.setTextRefernce(dateSelectTextField);
        calendar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        notes.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
        notes.setForeground(new Color(8, 83, 95));
        notes.setText("Notes");

        notesTField.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
        notesTField.setHorizontalAlignment(JTextField.CENTER);
        notesTField.setCursor(new Cursor(Cursor.HAND_CURSOR));
        notesTField.setHorizontalAlignment(JTextField.CENTER);

        cancelButton.setText("Cancel");
        cancelButton.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
        cancelButton.setBackground(new Color(0,153,204));
        cancelButton.setForeground(new Color(255, 255, 255));
        cancelButton.setBorderPainted(false);
        cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Home.enableRecBtn();
                dispose();
            }
        });

        saveButton.setText("Save");
        saveButton.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
        saveButton.setBackground(new Color(0, 153, 153));
        saveButton.setForeground(new Color(255, 255, 255));
        saveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        // <editor-fold defaultstate="collapsed" desc="DO NOT MODIFY - GROUP LAYOUT MANAGER">
        // Main Panel Layout 
        GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(categoryLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(paymentType)
                .addGap(70, 70, 70))
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(amountCB, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
                            .addComponent(categoryCB, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)))
                    .addGroup(GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(dateSelectTextField, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(dateLbl, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(paymentCB, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelCB, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
                            .addComponent(notesTField, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(24, Short.MAX_VALUE))
                    .addGroup(GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(notes)
                        .addGap(100, 100, 100))))
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveButton, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(amountLabel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelLabel)
                .addGap(100, 100, 100))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(amountLabel)
                    .addComponent(labelLabel))
                .addGap(5, 5, 5)
                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(amountCB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(categoryLabel)
                    .addComponent(paymentType))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(categoryCB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(paymentCB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(dateLbl)
                    .addComponent(notes))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(dateSelectTextField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                    .addComponent(notesTField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveButton, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        // Frame Layout Manager
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        // Frame Properties - cont
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        // </editor-fold>  

    }
    // for the transaction id to be unique by testing it from the database
    public static int generateRandom4DigitNumber() {
        while (true) {
            Random random = new Random();
            int randoms = 1000 + random.nextInt(9000);

            if (!isNumberExists(randoms)) {
                return randoms;
            }
        }
    }

    public static boolean isNumberExists(int number) {
        try (Connection connection = DriverManager.getConnection(postgres.DB_URL, postgres.USER, postgres.PASSWORD)) {
            String query = "SELECT transaction_id FROM public.transaction WHERE transaction_id = ?";
            PreparedStatement execute = connection.prepareStatement(query);
            execute.setInt(1, number);

            ResultSet result = execute.executeQuery();

            if (result.next()) {
                int existingNumber = result.getInt("transaction_id");
                JOptionPane.showMessageDialog(null, "Something went wrong please try again");
                System.out.println("Found one: " + existingNumber);
                return true;
            } else {
                //System.out.println("Didn't find any existing number.");
                return false;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
                                                   
    // Methods    --------------------------------------------------------------------------------------------------------------------------------------       
    
    

    // Method to get user ID (Replace this with your own method to get logged-in user's ID)
    private int getUserId() {
    // Replace this with your own method to get logged-in user's ID
         return Login.userIDVALUE();
}

    public void saveButtonActionPerformed(ActionEvent evt) {
        int transactionID = generateRandom4DigitNumber();

        String labelSelected = (String) labelCB.getSelectedItem();
        String categorySelected = (String) categoryCB.getSelectedItem();
        String paymentSelected = (String) paymentCB.getSelectedItem();
        String notes = notesTField.getText();

        
        String dateSelectTextFielded = dateSelectTextField.getText();

        try (Connection connection = DriverManager.getConnection(postgres.DB_URL, postgres.USER, postgres.PASSWORD)) {
            String amountSelected = (String) amountCB.getSelectedItem();
            BigDecimal amountVal = new BigDecimal(amountSelected);
            // Query 1 - Add Record
            String query = """
                INSERT INTO public.transaction (transaction_id, id, transaction_date, transaction_type, transaction_category, transaction_notes, money_type, transaction_amount) 
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                    """;
            PreparedStatement statement1 = connection.prepareStatement(query);
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");  // for the Date value to be grabbed

            Date utilDate;

            try {
                utilDate = sdf.parse(dateSelectTextFielded);
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

                statement1.setInt(1, transactionID);
                statement1.setInt(2, Login.userIDVALUE());
                statement1.setDate(3, sqlDate);
                statement1.setString(4, labelSelected);
                statement1.setString(5, categorySelected);
                statement1.setString(6, notes);
                statement1.setString(7, paymentSelected);  // money_type from postgres

                if (labelSelected.equals("EXPENSE")){
                    statement1.setBigDecimal(8, BigDecimal.valueOf(0).subtract(amountVal));
                } else {
                    statement1.setBigDecimal(8, amountVal);
                }

                int update = statement1.executeUpdate();
                if (update !=0){
                    System.out.println("\n Successfully inserted data to transactions");
                } else {
                    System.out.println("\n Something went wrong in inserting data to transactions");
                }

                // Refresh the database


            } catch (ParseException e) {
                e.printStackTrace();
            }

            // Query 2 - Retrieve Balance
            String sqlBal = "SELECT money_balance FROM public.client_users WHERE id = ?";
            PreparedStatement statement2 = connection.prepareStatement(sqlBal);
            statement2.setInt(1, Login.userIDVALUE());   // Replace 1 with the actual account_id you want to retrieve
            ResultSet resultSet = statement2.executeQuery();
            if (resultSet.next()) {
                balance = resultSet.getBigDecimal("money_balance");

                if (labelSelected.equals("EXPENSE")){
                    result = balance.subtract(amountVal);
                } else {
                    result = balance.add(amountVal);
                }

            }

            // Query 3 - Update Balance
            String sql = "UPDATE public.client_users SET money_balance = ? WHERE id = ?";
            PreparedStatement statement3 = connection.prepareStatement(sql);

            statement3.setBigDecimal(1, result);  // parameterIndex = Table Column Name

            statement3.setInt(2, Login.userIDVALUE());
                  
            int update =statement3.executeUpdate();

            if (update != 0){
                System.out.println("\n Updated Money Balance");
            } else {
                System.out.println("\n Something went wrong updating money balance");
            }


            // Refresh the Balance Counter up top


            if (Home.currentMonth == 6969) {
                System.out.println(Home.currentMonth);
                Home.allDateTotal();
            } else {
 
                Home.weeklyTotal();
                Home.monthtlyTotal();
            }
            Home.loadDataFromDatabase();
            Home.retrieveBalance();
            Home.enableRecBtn();
            dispose();
            
        }catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating balance.");
        }  catch (NumberFormatException f) {
            JOptionPane.showMessageDialog(this, "Invalid amount input. Please enter a valid number no letters.", "Error", JOptionPane.ERROR_MESSAGE);
        } 

    }




//AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA

// Method to update budget tracker on bill payment
@SuppressWarnings("unused")
private void updateBudgetTracker(BigDecimal amount) {
    try (Connection connection = DriverManager.getConnection(postgres.DB_URL, postgres.USER, postgres.PASSWORD)) {
        String query = "UPDATE budget_tracker SET balance = balance - ? WHERE user_id = ?";
        PreparedStatement pst = connection.prepareStatement(query);
        pst.setBigDecimal(1, amount);
        pst.setInt(2, getUserId()); // Adjust to get logged-in user's ID
        int update = pst.executeUpdate();
        if (update > 0) {
            JOptionPane.showMessageDialog(this, "Budget tracker updated successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update budget tracker.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error updating budget tracker.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}





public void addExpense(BigDecimal amount) {
    // Implement your expense addition logic here
    // This should be similar to your existing method for adding expenses
}








//2 aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
public void addExpenseRecord(BigDecimal amount) {
    int transactionID = generateRandom4DigitNumber();
    String categorySelected = "Bill Payment"; // or any category you prefer
    String paymentSelected = "Auto"; // or the payment method used
    String notes = "Bill Payment";

    try (Connection connection = DriverManager.getConnection(postgres.DB_URL, postgres.USER, postgres.PASSWORD)) {
        // Query 1 - Add Record
        String query = """
            INSERT INTO public.transaction (transaction_id, id, transaction_date, transaction_type, transaction_category, transaction_notes, money_type, transaction_amount) 
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;
        PreparedStatement statement = connection.prepareStatement(query);
        Date currentDate = new Date();
        java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());

        statement.setInt(1, transactionID);
        statement.setInt(2, Login.userIDVALUE());
        statement.setDate(3, sqlDate);
        statement.setString(4, "EXPENSE");
        statement.setString(5, categorySelected);
        statement.setString(6, notes);
        statement.setString(7, paymentSelected);  // money_type from postgres
        statement.setBigDecimal(8, BigDecimal.valueOf(0).subtract(amount));

        int update = statement.executeUpdate();
        if (update != 0) {
            System.out.println("Successfully inserted expense record.");
        } else {
            System.out.println("Failed to insert expense record.");
        }

        // Query 2 - Retrieve Balance
        String sqlBal = "SELECT money_balance FROM public.client_users WHERE id = ?";
        PreparedStatement statement2 = connection.prepareStatement(sqlBal);
        statement2.setInt(1, Login.userIDVALUE());
        ResultSet resultSet = statement2.executeQuery();
        if (resultSet.next()) {
            balance = resultSet.getBigDecimal("money_balance");
            result = balance.subtract(amount);
        }

        // Query 3 - Update Balance
        String sql = "UPDATE public.client_users SET money_balance = ? WHERE id = ?";
        PreparedStatement statement3 = connection.prepareStatement(sql);
        statement3.setBigDecimal(1, result);
        statement3.setInt(2, Login.userIDVALUE());

        int update2 = statement3.executeUpdate();
        if (update2 != 0) {
            System.out.println("Updated money balance.");
        } else {
            System.out.println("Failed to update money balance.");
        }

        // Refresh the Balance Counter up top
        Home.retrieveBalance();

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error updating balance.");
    }
}


}
