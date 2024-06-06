import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class FullDataOverview extends javax.swing.JFrame {
    static CheckConnection postgres = new CheckConnection();
    static String[] displayColumn;
    public FullDataOverview() {
        initComponents();
        total();
        load();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        fullTable = new javax.swing.JTable();
        profileLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jLabel4 = new javax.swing.JLabel();
       
        copyRightLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Full Data Overview");
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                
            }
        });


        fullTable.setFont(new java.awt.Font("Arial", 0, 12));
        fullTable.setToolTipText("Weekly");
        fullTable.setCursor(new Cursor(Cursor.HAND_CURSOR));
        fullTable.setRowHeight(25);
        fullTable.setShowGrid(true);
        fullTable.setFocusable(true);
        fullTable.setDragEnabled(true);
        fullTable.setAutoCreateRowSorter(true);
        fullTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        fullTable.getTableHeader().setReorderingAllowed(false); // Disable column reordering
        fullTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        fullTable.setRowHeight(30); // Adjust the value according to your preferences

        jScrollPane1.setViewportView(fullTable);

        profileLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 40)); // NOI18N
        profileLabel.setForeground(new java.awt.Color(0, 165, 230));
        profileLabel.setText("Salarium ");
        profileLabel.setAlignmentY(0.0F);

        jLabel2.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 165, 230));
        jLabel2.setText("FULL DATA OVERVIEW");

        jLabel4.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(5,139,93));
        jLabel4.setText("Total Expenses:");
        
        totalExpense = new javax.swing.JLabel();
        totalExpense.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N

        copyRightLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 10)); // NOI18N
        copyRightLabel.setForeground(new java.awt.Color(80, 80, 80));
        copyRightLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        copyRightLabel.setText("© 2023 Salarium LTD. ALL RIGHTS RESERVED");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalExpense, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(copyRightLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(383, 383, 383)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(360, 360, 360)
                        .addComponent(profileLabel)))
                .addGap(0, 367, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(profileLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(totalExpense))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(copyRightLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>                        

    public static void total(){
        try (Connection connection = DriverManager.getConnection(postgres.DB_URL, postgres.USER, postgres.PASSWORD)){
            String query = """
                    SELECT SUM(transaction_amount) AS total_expenses
                    FROM public.transaction
                    WHERE id = ?
                    AND transaction_type = 'EXPENSE';
                    """;
            PreparedStatement execute = connection.prepareStatement(query);
            execute.setInt(1, Login.userIDVALUE());
            ResultSet result = execute.executeQuery();

            if (result.next()){
                int totalExpenses = result.getInt("total_expenses");
                DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
                String stotal = decimalFormat.format(totalExpenses);
                totalExpense.setText(stotal);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void load() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        try (Connection connection = DriverManager.getConnection(postgres.DB_URL, postgres.USER, postgres.PASSWORD)) {
            String query2 = """ 
                SELECT * 
                FROM public.transaction
                WHERE id = ? 
                ORDER BY transaction_date DESC
                    """;
            PreparedStatement statement = connection.prepareStatement(query2);
            statement.setInt(1, Login.userIDVALUE());


            ResultSet resultSet2 = statement.executeQuery();
            // Set the JTable's model to the non-editable model
            fullTable.setModel(buildTable(resultSet2));

            // Apply the center renderer to all columns
            for (int i = 0; i < fullTable.getColumnCount(); i++) {
                fullTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // Method to disable single cell editable in my JTable
    public static DefaultTableModel build(ResultSet resultSet) throws SQLException {
        return new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };
    }

    // Method for occupying the table from the PostgreSQL
    public static DefaultTableModel buildTable(ResultSet resultSet) throws SQLException {
        
        ResultSetMetaData databaseData = resultSet.getMetaData();
    
        int columnCount = databaseData.getColumnCount();
        String[] originalColumnNames = new String[columnCount];
        displayColumn = new String[columnCount - 1]; // Adjusted for hiding two columns
    
        Map<String, String> columnMapping = new HashMap<>();
        columnMapping.put("transaction_id", "Transaction No.");
        columnMapping.put("transaction_date", "Date");
        columnMapping.put("transaction_type", "Income / Expense");
        columnMapping.put("transaction_category", "Category");
        columnMapping.put("transaction_notes", "Notes");
        columnMapping.put("money_type", "Type");
        columnMapping.put("transaction_amount", "Amount");
    
        int[] hiddenColumnIndices = {2}; // Indices of columns to be hidden
    
        int hiddenColumnsCount = hiddenColumnIndices.length;
    
        for (int i = 1, j = 0, hiddenCount = 0; i <= columnCount; i++) {
            originalColumnNames[i - 1] = databaseData.getColumnName(i);
            if (hiddenCount < hiddenColumnsCount && i == hiddenColumnIndices[hiddenCount]) {
                // Skip the hidden column
                hiddenCount++;
            } else {
                displayColumn[j++] = columnMapping.getOrDefault(originalColumnNames[i - 1], originalColumnNames[i - 1]);
            }
        }
    
        DefaultTableModel tableModel = new DefaultTableModel(displayColumn, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };

        while (resultSet.next()) {
            Object[] rowData = new Object[columnCount - hiddenColumnsCount];
            for (int i = 1, j = 0, hiddenCount = 0; i <= columnCount; i++) {
                if (hiddenCount < hiddenColumnsCount && i == hiddenColumnIndices[hiddenCount]) {
                    // Skip the hidden column
                    hiddenCount++;
                } else {
                    rowData[j++] = resultSet.getObject(i);
                }
            }
            tableModel.addRow(rowData);
        }
        return tableModel;
    }

    // Variables declaration - do not modify                     
    private javax.swing.JLabel copyRightLabel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    static javax.swing.JLabel totalExpense;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    static javax.swing.JTable fullTable;
    private javax.swing.JLabel profileLabel;
    // End of variables declaration                   
}
