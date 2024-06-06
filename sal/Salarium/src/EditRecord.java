import java.awt.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

import com.raven.datechooser.DateChooser;

import java.awt.event.*;
import java.math.BigDecimal;


// same rani sila sa add new record window but for editing langs

public class EditRecord extends JFrame {
    // SQL connection - Modify (localhost:2001/postgres) to your own info.
    static CheckConnection postgres = new CheckConnection();
    static String defAmount;
    static  int amountVal, index;
    Connection connection = null;

    // Class Global Variables / Attributes
    JComboBox<Object> amountCB;
    JComboBox<Object> labelCB;
    JComboBox<Object> categoryCB;
    JComboBox<Object> paymentCB;
    JTextField dateSelectTextField;
    JTextField notesTField;
    String modifiedText;
     // edit record balance
    static BigDecimal AmountVal, balance, result, cashBalance;
    
    // Constructor | Methods Intializer
    public EditRecord() {
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
        JButton updateButton = new JButton();
        JButton deleteButton = new JButton();
        JLabel labelLabel = new JLabel();
        JLabel categoryLabel = new JLabel();
        JLabel paymentType = new JLabel();
        JLabel dateLbl = new JLabel();
        JLabel notes = new JLabel();
        
        // Panel
        JPanel mainPanel = new JPanel();


        // Frame Properties
        setTitle("Update Record");
        setResizable(false);
        setVisible(true);
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
        defAmount = String.valueOf(Home.amountValue); // default amount
        char targetChar = '-';
        index = defAmount.indexOf(targetChar);
        // to remove the minus char
        if (index != -1) {
            modifiedText = defAmount.replace(String.valueOf(targetChar), "");
        } else {
            modifiedText = defAmount;
        }

        amountCB.setSelectedItem(modifiedText);

        labelLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
        labelLabel.setForeground(new Color(8, 83, 95));
        labelLabel.setText("Label");
        labelLabel.setHorizontalTextPosition(SwingConstants.CENTER);

        labelCB.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
        String defLabel = Home.labelType;
        labelCB.setModel(new DefaultComboBoxModel<>(new String[] {"EXPENSE", "INCOME"})); // Additional to code
        labelCB.setSelectedItem(defLabel);
        labelCB.setCursor(new Cursor(Cursor.HAND_CURSOR));

        categoryLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
        categoryLabel.setForeground(new Color(8, 83, 95));
        categoryLabel.setText("Category");

        categoryCB.setEditable(true);
        String defcatCB = Home.category;
        categoryCB.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
        categoryCB.setModel(new DefaultComboBoxModel<>(new String[]
        { "Others", "Food and Drinks", "Bar, Cafe", "Groceries", "Fast-Food",
        "Shopping", "Clothes & Shoes", "Drug-store Shabu", "Electronics, accessories",
        "Free Time", "Gifts, joy", "Health & beauty", "Home, garden", "Jewels, accessories", "Kids",
        "Pets, animals", "Tools", "Transportation", "Business Trips", "Long Distance", "Public Transport", "Taxi",
        "Life & Entertainment","Active sport, fitness", "Alcohol, tabacco", "Books, audio, and subscription",
        "Charity, gifts", "Culture, sport events", "Education, Development", "Health care, doctor", 
        "Hobbies", "Holiday, trips, hotels", "Life Events", "Lottery, gambling", "Whore house", "Drugs, woman", "Wellness, beauty",
        "Communication, PC", "Internet", "Phone, cell phone", "Postal service", "Software, apps, games", "Financial Expenses",
        "Advisory", "Charges, fees", "Child support", "Concubine Support", "Insurances", "Loan, interest",
        "Taxes"}
         ));
        categoryCB.setSelectedItem(defcatCB);
        categoryCB.setCursor(new Cursor(Cursor.HAND_CURSOR));

        paymentType.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
        paymentType.setForeground(new Color(8, 83, 95));
        paymentType.setText("Payment Type");

        String defPay = Home.paymentType;
        paymentCB.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
        paymentCB.setModel(new DefaultComboBoxModel<>(new String[] { "Cash", "Gcash", "Credit Card"}));
        paymentCB.setSelectedItem(defPay);
        paymentCB.setCursor(new Cursor(Cursor.HAND_CURSOR));

        dateLbl.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
        dateLbl.setForeground(new Color(8, 83, 95));
        dateLbl.setText("Date");

        dateSelectTextField.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
        dateSelectTextField.setHorizontalAlignment(JTextField.CENTER);

        //dateSelectTextField.setVisible(false);
        //dateSelectTextField.setEnabled(false);
        //dateSelectTextField.setEditable(false);
        calendar.setTextRefernce(dateSelectTextField);
        String dt = Home.myDate;
        dateSelectTextField.setText(dt);
        dateSelectTextField.setCursor(new Cursor(Cursor.HAND_CURSOR));

        notes.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
        notes.setForeground(new Color(8, 83, 95));
        notes.setText("Notes");

        notesTField.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
        notesTField.setText(Home.transacNotes);
        notesTField.setToolTipText("Enter your side notes");
        notesTField.setHorizontalAlignment(JTextField.CENTER);
        notesTField.setCursor(new Cursor(Cursor.HAND_CURSOR));

        deleteButton.setText("Delete Record");
        deleteButton.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
        deleteButton.setBackground(new Color(255,41,67));
        deleteButton.setForeground(new Color(255, 255, 255));
        deleteButton.setBorderPainted(false);
        deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                deleteAction(evt);
            }
        });

        updateButton.setText("Update");
        updateButton.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
        updateButton.setBackground(new Color(0, 153, 153));
        updateButton.setForeground(new Color(255, 255, 255));
        updateButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                updateButtonActionPerformed(evt);
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
                .addGap(30, 30, 30)
                .addComponent(deleteButton, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(updateButton, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(deleteButton, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateButton, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
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
                                       
    // Methods
    // DELETE BUTTON
    public void deleteAction(ActionEvent evt){
        int transactionID = Home.selectedValue;
        String amountSelected = (String) amountCB.getSelectedItem();
        String labelSelected = (String) labelCB.getSelectedItem();
        BigDecimal amountVal = new BigDecimal(amountSelected);

        int choice = JOptionPane.showConfirmDialog(null, "Do you really wanna delete this record?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                try (Connection connection = DriverManager.getConnection(postgres.DB_URL, postgres.USER, postgres.PASSWORD)){

                    // Q1
                    String delQuery = "DELETE FROM public.transaction WHERE transaction_id = ?";
                    PreparedStatement statementdel = connection.prepareStatement(delQuery);
                    statementdel.setInt(1, transactionID);

                    int rowsDeleted = statementdel.executeUpdate();

                    if (rowsDeleted > 0){
                        System.out.println("Succesfully Deleted");
                    } else {
                        System.out.println("Somethings wrong upon deleting");
                    }

                    // Query 2 - Retrieve Balance
                    String sqlBal = "SELECT money_balance FROM public.client_users WHERE id = ?";
                    PreparedStatement statement2 = connection.prepareStatement(sqlBal);
                    statement2.setInt(1, Login.userIDVALUE());   // Replace 1 with the actual account_id you want to retrieve
                    ResultSet resultSet = statement2.executeQuery();
                    if (resultSet.next()) {
                        //To be grabbed below
                        balance = resultSet.getBigDecimal("money_balance");
                    }


                    // Q3 - Update balnace in client_users
                    String refundQuery  = "UPDATE public.client_users SET money_balance = ? WHERE id = ?";
                    PreparedStatement refundStatement = connection.prepareStatement(refundQuery);

                    // Refund
                    if (labelSelected.equals("EXPENSE")) { 
                        result = balance.add(amountVal);
                        refundStatement.setBigDecimal(1, result);
                        System.out.println("Account balance expense refunded amount: " + amountVal);
                        
                    } else {
                        // If original amount is greater than to be remove retain the original amount 
                        if (balance.compareTo(amountVal) < 0) {
                            refundStatement.setBigDecimal(1, result);
                            System.out.println("Original Amount Retained");
                        } else {
                            BigDecimal updatedResult = balance.subtract(amountVal);
                            refundStatement.setBigDecimal(1, updatedResult);
                            System.out.println("Account balance income removed amount: " + amountVal);
                        }
                    }

                    refundStatement.setInt(2, Login.userIDVALUE());

                    int rowsRefunded = refundStatement.executeUpdate();

                    if (rowsRefunded > 0) {
                        System.out.println("Refunded Succesfully ");
                    } else {
                        System.out.println("Something went wrong.");
                    }

                    Home.retrieveBalance();
                    Home.loadDataFromDatabase();
                    Home.enableRecBtn();
                    dispose();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

    }
    // UPDATE BUTTON
    public void updateButtonActionPerformed(ActionEvent evt) {
        // import the grab column ID
        int transactionID = Home.selectedValue; 
        // Purposely placed the variable here instad of placing it in the local para ma call nako to both delete and update since ang pag initiate sa value is dli sabay
        String amountSelected = (String) amountCB.getSelectedItem();
        BigDecimal amountVal = new BigDecimal(amountSelected);


        String dateSelectTextFielded = dateSelectTextField.getText();


        String labelSelected = (String) labelCB.getSelectedItem();
        String categorySelected = (String) categoryCB.getSelectedItem();
        String paymentSelected = (String) paymentCB.getSelectedItem();
        String notes = notesTField.getText();

        try (Connection connection = DriverManager.getConnection(postgres.DB_URL, postgres.USER, postgres.PASSWORD)) {

            // Query 1 - Updated Record from the transaction table
            String query = """
                UPDATE public.transaction SET transaction_date = ?, transaction_type = ?, transaction_category = ?, transaction_notes = ?, money_type = ?, transaction_amount = ? 
                WHERE transaction_id = ?
                    """;
            PreparedStatement statement1 = connection.prepareStatement(query);

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");  // for the Date value to be grabbed

            Date utilDate;
            
            utilDate = sdf.parse(dateSelectTextFielded);
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            statement1.setDate(1, sqlDate);
            statement1.setString(2, labelSelected);
            statement1.setString(3, categorySelected);
            statement1.setString(4, notes);
            statement1.setString(5, paymentSelected);  // money_type from postgres

            if (labelSelected.equals("EXPENSE")){
                statement1.setBigDecimal(6, BigDecimal.valueOf(0).subtract(amountVal));
            } else {
                statement1.setBigDecimal(6, amountVal);
            }
            statement1.setInt(7, transactionID);

            int rowUpdated = statement1.executeUpdate();

            if (rowUpdated > 0) {
                System.out.println("Row updated successfully!");
            } else {
                System.out.println("No rows updated. Check if the specified condition is met.");
            }


            // Q2 Retrieve amount from the balance table
            String sql = "SELECT money_balance FROM public.client_users WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, Login.userIDVALUE()); // Replace 1 with the actual user_id you want to retrieve
            ResultSet resultSet = statement.executeQuery();

            if ( resultSet.next()){
                cashBalance = resultSet.getBigDecimal("money_balance");
            }
            
 
            // Q3 Update record from the balance table
            BigDecimal firstAmount = Home.amountValue;
            String update = "UPDATE public.client_users SET money_balance = ? WHERE id = ?";
            PreparedStatement updateStatement = connection.prepareStatement(update);

            if (labelSelected.equals("EXPENSE")) { // expense
                // para mo match ug negative
                BigDecimal originalAmount = BigDecimal.valueOf(0).subtract(amountVal);

                System.out.println(originalAmount);

                if (firstAmount == originalAmount){
                    updateStatement.setInt(1, 0);
                    System.out.println("Amount are the same");

                } else if (firstAmount.compareTo(originalAmount) < 0) { 
                    BigDecimal res = firstAmount.subtract(originalAmount); // get the difference between them
                    BigDecimal result = cashBalance.subtract(res);

                    System.out.println("difference: "+res);
                    System.out.println(result);

                    updateStatement.setBigDecimal(1, result);
                    System.out.println("Cash Balance was added");

                } else {// firstAmount > originalAmount
                    BigDecimal res = originalAmount.subtract(firstAmount); // get the differnce between them
                    BigDecimal result = cashBalance.add(res);

                    System.out.println("difference: "+res);
                    System.out.println(result);

                    updateStatement.setBigDecimal(1, result);
                    System.out.println("Cash Balance was further reduced to negative");
                }

                updateStatement.setInt(2, Login.userIDVALUE()); 

            } else { // INCOME
                if (firstAmount == amountVal){
                    updateStatement.setInt(1, 0);
                    System.out.println("Amount are the same");

                } else if (firstAmount.compareTo(amountVal) > 0) {
                    BigDecimal res = firstAmount.subtract(amountVal);
                    BigDecimal result  = cashBalance.subtract(res);
                    System.out.println("difference: "+res);
                    updateStatement.setBigDecimal(1, result);
                } else {
                    BigDecimal res = amountVal.subtract(firstAmount);
                    BigDecimal result = cashBalance.add(res);
                    System.out.println("difference: "+res);
                    updateStatement.setBigDecimal(1, result);
                }

                updateStatement.setInt(2, Login.userIDVALUE());
            }

            int rowsUpdated = updateStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Balance Updated Successfully");
            } else {
                System.out.println("Something went wrong.");
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
        } catch (ParseException e) {
            e.printStackTrace();
        } 

    }
}
