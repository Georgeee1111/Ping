import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NewBal extends JPanel {

    // Variables declaration - do not modify                     
    JButton saveBtn;
    JLabel amounLabel;
    static JTextField amountTF;
    static CheckConnection postgres = new CheckConnection();
    static JFrame newBalFrame;

    // Constructor
    public NewBal() {
        initComponents();
    }

                       
    private void initComponents() {

        amounLabel = new JLabel();
        amountTF = new JTextField();
        saveBtn = new JButton();

        setToolTipText("");
        setLayout(null);

        amounLabel.setFont(new Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        amounLabel.setForeground(new Color(8, 83, 95));
        amounLabel.setText("Amount: ");
        add(amounLabel);
        amounLabel.setBounds(10, 30, 60, 17);

        amountTF.setFont(new Font("Arial Rounded MT Bold", 0, 12)); // NOI18N

        add(amountTF);
        amountTF.setBounds(80, 30, 130, 22);

        saveBtn.setFont(new Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        saveBtn.setText("Save");
        saveBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });
        add(saveBtn);
        saveBtn.setBounds(140, 60, 70, 23);
    }                      

    // Method    
    private void saveBtnActionPerformed(ActionEvent evt) { 
        try (Connection connection = DriverManager.getConnection(postgres.DB_URL, postgres.USER, postgres.PASSWORD)) {
            String amount = amountTF.getText();
            int value = Integer.parseInt(amount);
            System.out.println(value);
                // query 1
                if (amount.isEmpty() || amount.isBlank()) {
                    System.out.println("Blank Input");
                    
                }else{
                        String sql = "UPDATE public.account_balance SET balance = ? WHERE user_id = ?";
                        PreparedStatement statement = connection.prepareStatement(sql);
                        statement.setInt(1, value);  // parameterIndex = Table Column Name
                        statement.setInt(2, Login.userIDVALUE());         
                        int rowsUpdated = statement.executeUpdate();
                        if (rowsUpdated > 0) {
                            Home.retrieveBalance();
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Something went wrong", "Error", JOptionPane.ERROR_MESSAGE);
                        }


                        // query2 
                        java.sql.Date currentDate = new java.sql.Date(new Date().getTime());
                        String query2 = """

                            INSERT INTO public.transaction (user_id, transaction_date, transaction_type, transaction_notes, money_type, transaction_amount)
                            VALUES (?, ?, ?, ?, ?, ?)

                                    """;
                        try (PreparedStatement statement2 = connection.prepareStatement(query2)) {
                        statement2.setInt(1, Login.userIDVALUE());
                        statement2.setDate(2, currentDate);
                        statement2.setString(3, "NEW BALANCE");
                        statement2.setString(4, "NEW BALANCE");
                        statement2.setString(5, "Not Specified");
                        statement2.setInt(6, value);

                        statement2.executeUpdate();
                        Home.loadDataFromDatabase();
                        newBalFrame.dispose();
                        
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid balance input. Please enter a valid number no letters.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating balance.");
        }
          

    }                
}
