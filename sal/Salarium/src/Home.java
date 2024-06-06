import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatGradiantoMidnightBlueIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatLightOwlIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialDeepOceanIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialLighterIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatSolarizedLightIJTheme;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Home extends javax.swing.JFrame {
    static BigDecimal balance;
    static CheckConnection postgres = new CheckConnection();
    static String[] displayColumnNames;
    static DefaultTableModel model;
    static int selectedRow, selectedValue;
    static BigDecimal amountValue;

    static String labelType = "Default";
    static String category = "Default";
    static String paymentType = "Default";
    static String transacNotes =  "Default";
    static Date dates;
    static String myDate;
    static int month, year; // for the ComboBox
    static int currentMonth;



    public Home() {
        initComponents();
        currentUser();
        retrieveBalance();
        weeklyTotal();
        monthtlyTotal();

        loadDataFromDatabase();
    }
      
    private void initComponents() {
        fullData = new javax.swing.JMenuItem();
        deepOcean = new javax.swing.JMenuItem();
        solarized = new javax.swing.JMenuItem();
        lightOwl = new javax.swing.JMenuItem();
        materialLighter = new javax.swing.JMenuItem();
        arcDark = new javax.swing.JMenuItem();
        midNightBlue = new javax.swing.JMenuItem();
        themesJMenu = new javax.swing.JMenu();
        macOSlight = new javax.swing.JMenuItem();
        macOSdark = new javax.swing.JMenuItem();
        mainPanel = new javax.swing.JPanel();
        profileLabel = new javax.swing.JLabel();
        statisticsLBL = new javax.swing.JLabel();
        notesLbl = new javax.swing.JLabel();
        weeklySpendings = new javax.swing.JLabel();
        days7Lbl1 = new javax.swing.JLabel();
        monthlySpendings = new javax.swing.JLabel();
        days30Lbl = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        currentUserLogin = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cashAmount = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        weeklyExLbl = new javax.swing.JLabel();
        weeklyExAmount = new javax.swing.JLabel();
        monthlyExAmount = new javax.swing.JLabel();
        copyRightLabel = new javax.swing.JLabel();
        addRecord = new javax.swing.JButton();
        monthCB = new javax.swing.JComboBox<>();
        yearCB = new javax.swing.JComboBox<>();
        wklySpndngSP = new javax.swing.JScrollPane();
        wklySpndngsTble = new javax.swing.JTable();
        mnthlySpndngSP = new javax.swing.JScrollPane();
        mnthlySpndingsTable = new javax.swing.JTable();
        userName = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        mAccountMenu = new javax.swing.JMenu();
        newBalMenuItem = new javax.swing.JMenuItem();
        settingsMenuItem = new javax.swing.JMenuItem();
        logoutMenuItem = new javax.swing.JMenuItem();
        optionMenu = new javax.swing.JMenu();
        refreshMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();

        LocalDate currentDate = LocalDate.now();
        currentMonth = currentDate.getMonthValue();
        
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        // Window Close Listener Handler
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to leave the app?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    
                }
            }
        });

        // MenuBar
        mAccountMenu.setText("My Account");

        newBalMenuItem.setText("Edit Balance");
        newBalMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newBalMenuItemActionPerformed(evt);
            }
        });
        mAccountMenu.add(newBalMenuItem);

        settingsMenuItem.setText("Settings");
        settingsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsMenuItemActionPerformed(evt);
            }
        });
        mAccountMenu.add(settingsMenuItem);

        logoutMenuItem.setText("Logout");
        logoutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutMenuItemActionPerformed(evt);
            }
        });
        mAccountMenu.add(logoutMenuItem);
        menuBar.add(mAccountMenu);

        // S

        optionMenu.setText("Options");

        refreshMenuItem.setText("Refresh");
        refreshMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshMenuItemActionPerformed(evt);
            }
        });
        optionMenu.add(refreshMenuItem);

        fullData.setText("Full Data Table");
        fullData.addActionListener(e -> new FullDataOverview().setVisible(true));
        optionMenu.add(fullData);

        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        optionMenu.add(exitMenuItem);
        menuBar.add(optionMenu);

        // Themes
        themesJMenu.setText("Themes");

        macOSlight.setText("Light Mode (Default)");
        macOSlight.addActionListener(e -> changeTheme("Light"));
        themesJMenu.add(macOSlight);
        
        materialLighter.setText("Material Lighter");
        materialLighter.addActionListener(e -> changeTheme("Material Lighter"));
        themesJMenu.add(materialLighter);
        
        lightOwl.setText("Light Owl");
        lightOwl.addActionListener(e -> changeTheme("Light Owl"));
        themesJMenu.add(lightOwl);

        solarized.setText("Solarized Light");
        solarized.addActionListener(e -> changeTheme("Solarized Light"));
        themesJMenu.add(solarized);

        macOSdark.setText("Dark Mode");
        macOSdark.addActionListener(e -> changeTheme("Dark"));
        themesJMenu.add(macOSdark);

        midNightBlue.setText("Midnight Blue");
        midNightBlue.addActionListener(e -> changeTheme("midnight blue"));
        themesJMenu.add(midNightBlue);

        arcDark.setText("Arc Dark");
        arcDark.addActionListener(e -> changeTheme("Arc Dark"));
        themesJMenu.add(arcDark);

        deepOcean.setText("Deep Ocean");
        deepOcean.addActionListener(e -> changeTheme("Deep Ocean"));
        themesJMenu.add(deepOcean);

        menuBar.add(themesJMenu);
        setJMenuBar(menuBar);

        mainPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        profileLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 40)); 
        profileLabel.setForeground(new java.awt.Color(0, 165, 230));
        profileLabel.setText("Salarium ");

        statisticsLBL.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); 
        statisticsLBL.setForeground(new java.awt.Color(0, 105, 150));
        statisticsLBL.setText("All Time Statistics");

        notesLbl.setFont(new java.awt.Font("Arial", 2, 12)); 
        notesLbl.setText("Displayed are your account's all time statistics.");

        weeklySpendings.setFont(new java.awt.Font("Arial Unicode MS", 0, 18)); 
        weeklySpendings.setText("Weekly Expenses");

        days7Lbl1.setFont(new java.awt.Font("Arial", 2, 10)); 
        days7Lbl1.setText("LAST 7 DAYS");

        monthlySpendings.setFont(new java.awt.Font("Arial Unicode MS", 0, 18)); 
        monthlySpendings.setText("Monthly Expenses");

        days30Lbl.setFont(new java.awt.Font("Arial", 2, 10)); 
        days30Lbl.setText("LAST 30 DAYS");

        jLabel4.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); 
        jLabel4.setForeground(new java.awt.Color(0, 105, 105));
        jLabel4.setText(""); // Total Expenses:
    
        currentUserLogin.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 17)); 
        currentUserLogin.setForeground(new java.awt.Color(0, 165, 230));
        currentUserLogin.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        currentUserLogin.setText("Current User");

        userName.setFont(new java.awt.Font("Arial Rounded MT", 0, 12));
        userName.setForeground(new java.awt.Color(255, 152, 67));
        userName.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        userName.setText(""); 

        jLabel11.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); 
        jLabel11.setText(""); // 52,340.00

        jLabel23.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); 
        jLabel23.setForeground(new java.awt.Color(255, 152, 67));
        jLabel23.setText(""); //Highest Monthly Expenses:

        jLabel10.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); 
        jLabel10.setForeground(new java.awt.Color(0, 105, 150));
        jLabel10.setText(""); //All Time Average Expenses:

        jLabel2.setFont(new java.awt.Font("Arial", 2, 12)); 
        jLabel2.setForeground(new java.awt.Color(0, 165, 230));
        jLabel2.setText("Profile Data Summary");

        jLabel3.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 20)); 
        jLabel3.setForeground(new java.awt.Color(0, 165, 230));
        jLabel3.setText("Cash Balance: ");

        cashAmount.setFont(new java.awt.Font("Arial", 0, 20)); 

        jLabel7.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); 
        jLabel7.setForeground(new java.awt.Color(5, 139, 93));
        jLabel7.setText("Total Monthly Expenses: ");

        weeklyExLbl.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); 
        weeklyExLbl.setForeground(new java.awt.Color(5, 139, 93));
        weeklyExLbl.setText("Total Weekly Expenses: ");

        weeklyExAmount.setFont(new java.awt.Font("Arial", 0, 12)); 
        monthlyExAmount.setFont(new java.awt.Font("Arial", 0, 12)); 

        copyRightLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 10)); 
        copyRightLabel.setForeground(new java.awt.Color(80, 80, 80));
        copyRightLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        copyRightLabel.setText("© 2023 Salarium LTD. ALL RIGHTS RESERVED");

        addRecord.setBackground(new java.awt.Color(0, 165, 230));
        addRecord.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); 
        addRecord.setForeground(new java.awt.Color(255, 255, 255));
        addRecord.setText("Add Record");
        addRecord.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addRecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRecordActionPerformed(evt);
            }
        });

        monthCB.setFont(new java.awt.Font("Arial", 0, 12)); 
        monthCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"All","January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}));
        monthCB.setSelectedIndex(1);
        monthCB.setToolTipText("");
        monthCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthCBActionPerformed(evt);
            }
        });
        yearCB.setVisible(false);
        yearCB.setEnabled(false);
        yearCB.setFont(new java.awt.Font("Arial", 0, 12)); 
        yearCB.setToolTipText("");
        yearCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"All", "2024", "2025", "2026"}));
        yearCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //yearCBActionPerformed(evt);
            }
        });
        // Tables
        wklySpndngsTble.setFont(new java.awt.Font("Arial", 0, 12));
        wklySpndngsTble.setToolTipText("Weekly");
        wklySpndngsTble.setCursor(new Cursor(Cursor.HAND_CURSOR));
        wklySpndngsTble.setRowHeight(25);
        wklySpndngsTble.setShowGrid(true);
        wklySpndngsTble.setFocusable(true);
        wklySpndngsTble.setDragEnabled(true);
        wklySpndngsTble.setAutoCreateRowSorter(true);
        wklySpndngsTble.getTableHeader().setReorderingAllowed(false); // Disable column reordering
        wklySpndngsTble.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        wklySpndngsTble.setRowHeight(30); 
        ListSelectionModel wklySelectionModel = wklySpndngsTble.getSelectionModel();
        wklySelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        wklySpndngsTble.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                weeklyTableSelected(evt);
            }
        });
        wklySpndngSP.setViewportView(wklySpndngsTble);

        mnthlySpndingsTable.setFont(new java.awt.Font("Arial", 0, 12)); 
        mnthlySpndingsTable.setToolTipText("Monthly");
        mnthlySpndingsTable.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mnthlySpndingsTable.setRowHeight(25);
        mnthlySpndingsTable.setShowGrid(true);
        mnthlySpndingsTable.setFocusable(true);
        mnthlySpndingsTable.setDragEnabled(true);
        mnthlySpndingsTable.setAutoCreateRowSorter(true);
        mnthlySpndingsTable.getTableHeader().setReorderingAllowed(false); // Disable column reordering
        mnthlySpndingsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        mnthlySpndingsTable.setRowHeight(30); 
        ListSelectionModel mnthlySelectionModel = mnthlySpndingsTable.getSelectionModel();
        mnthlySelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mnthlySpndingsTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                monthlyTableSelected(evt);
            }
        });
        mnthlySpndngSP.setViewportView(mnthlySpndingsTable);

        // <editor-fold defaultstate="collapsed" desc="DO NOT MODIFY - GROUP LAYOUT MANAGER">
        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(copyRightLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 940, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, mainPanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(mainPanelLayout.createSequentialGroup()
                                    .addComponent(weeklySpendings)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(days7Lbl1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(weeklyExLbl)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(weeklyExAmount))
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(addRecord)
                                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(wklySpndngSP, javax.swing.GroupLayout.PREFERRED_SIZE, 940, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(mainPanelLayout.createSequentialGroup()
                                            .addGap(6, 6, 6)
                                            .addComponent(monthlySpendings)
                                            .addGap(7, 7, 7)
                                            .addComponent(days30Lbl)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel7)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(monthlyExAmount))
                                        .addComponent(mnthlySpndngSP, javax.swing.GroupLayout.PREFERRED_SIZE, 940, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel23))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(currentUserLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(userName, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(0, 20, Short.MAX_VALUE))
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(397, 397, 397)
                        .addComponent(profileLabel))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cashAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(statisticsLBL)
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addComponent(notesLbl)
                                        .addGap(18, 18, 18)
                                        .addComponent(monthCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(yearCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(profileLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(currentUserLogin)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(userName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cashAmount))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(statisticsLBL)))
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(monthCB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(yearCB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(notesLbl)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(weeklySpendings, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(days7Lbl1)
                    .addComponent(weeklyExLbl)
                    .addComponent(weeklyExAmount)
                    .addComponent(addRecord))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(wklySpndngSP, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(monthlySpendings, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(days30Lbl)
                    .addComponent(jLabel7)
                    .addComponent(monthlyExAmount))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mnthlySpndngSP, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(copyRightLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
        // </editor-fold>


    }// </editor-fold>                        

    // METHODS
    private void settingsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {  
        new AccountSettings().setVisible(true);
    }                                                

    private void refreshMenuItemActionPerformed(java.awt.event.ActionEvent evt) { 
        // Refresh all the information from the database
        retrieveBalance();  
        weeklyTotal();
        monthtlyTotal();                             
        loadDataFromDatabase();
    }
    private void addRecordActionPerformed(java.awt.event.ActionEvent evt) {                                   
        new AddNewRecord().setVisible(true);
    }                                   

    private void logoutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                               
        int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            
            new Login().setVisible(true);
            try (Connection connection = DriverManager.getConnection(postgres.DB_URL, postgres.USER, postgres.PASSWORD)) {

                String query = "INSERT INTO public.login_history (id, login_state) VALUES (?, ?)";
    
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setInt(1, Login.userIDVALUE());
                    statement.setBoolean(2, false);

                    int confirmation = statement.executeUpdate();

                    if (confirmation > 0){
                        System.out.println("Successfully Logged out! User_ID: " + Login.userIDVALUE());
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

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        int result = JOptionPane.showConfirmDialog(null, "Changes you made may not be saved. ", "Exit app?", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                System.exit(0);
                
            }                                             
        
    }                                          
    // Edit Balance
    private void newBalMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        String input = JOptionPane.showInputDialog("Edit Initial Balance:");
        try (Connection connection = DriverManager.getConnection(postgres.DB_URL, postgres.USER, postgres.PASSWORD)){
            // Parse the input string to an integer
            BigDecimal bigDecimalValue = new BigDecimal(input);

            String sql = "UPDATE public.client_users SET money_balance = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setBigDecimal(1, bigDecimalValue);  // parameterIndex = Table Column Name
            statement.setInt(2, Login.userIDVALUE()); 

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                retrieveBalance();
            }
            else {
                JOptionPane.showMessageDialog(null, "Something went wrong", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            
        } catch (SQLException e ){
            e.printStackTrace();
        } catch (NumberFormatException e) {
            // Handle the case where the input is not a valid integer
            if (input == null){
                System.out.println("Null Input / No value is given");
            } else if (input.length() == 0){
                System.out.println("Null Input / No value is given");
            } else {
                JOptionPane.showMessageDialog(null, "Invalid input! Please enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    // Custom Methods
    // Cash Balance value update
    public static void retrieveBalance() {

        try (Connection connection = DriverManager.getConnection(postgres.DB_URL, postgres.USER, postgres.PASSWORD)) {
            String sql = "SELECT money_balance FROM public.client_users WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, Login.userIDVALUE()); // Replace 1 with the actual user_id you want to retrieve
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                
                balance = resultSet.getBigDecimal("money_balance");
                
                // set the color to red if the amount is negative zero
                if (balance.compareTo(new BigDecimal("-1")) < 0){
                    cashAmount.setForeground(new java.awt.Color(255, 104, 104));
                }  else {
                    cashAmount.setForeground(null);
                }

                // Create a DecimalFormat instance with the desired format
                if (balance != null) {
                    DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
                    String total = decimalFormat.format(balance);
                    cashAmount.setText(total);
                } else {
                    // Handle the case where totalExpenses is null, set an appropriate default or handle accordingly
                    weeklyExAmount.setText("N/A");  // You can set a default value or handle it based on your requirements
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }   

    }

    private void monthCBActionPerformed(java.awt.event.ActionEvent evt) {    
        String monthName = (String) monthCB.getSelectedItem();
        System.out.println(monthName);

        int monthNumber = -1;
        switch (monthName.toLowerCase()) {
            case "all":
                monthNumber = 6969;
                break;
            case "january":
                monthNumber = 1;
                break;
            case "february":
                monthNumber = 2;
                break;
            case "march":
                monthNumber = 3;
                break;
            case "april":
                monthNumber = 4;
                break;
            case "may":
                monthNumber = 5;
                break;
            case "june":
                monthNumber = 6;
                break;
            case "july":
                monthNumber = 7;
                break;
            case "august":
                monthNumber = 8;
                break;
            case "september":
                monthNumber = 9;
                break;
            case "october":
                monthNumber = 10;
                break;
            case "november":
                monthNumber = 11;
                break;
            case "december":
                monthNumber = 12;
                break;
            default:

                break;
        }
        currentMonth = monthNumber;

        if (currentMonth == 6969) {
            System.out.println(currentMonth);
            allDateTotal();
            loadDataFromDatabase();
        } else {
            loadDataFromDatabase();
            weeklyTotal();
            monthtlyTotal();
        }


    }  
                      
    public static void loadDataFromDatabase() {
        // Center every data in the table
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        String monthName = (String) monthCB.getSelectedItem();

        if (monthName.isEmpty() || monthName.equalsIgnoreCase("All")){ // default 7 & 30 days without specific month
            try (Connection connection = DriverManager.getConnection(postgres.DB_URL, postgres.USER, postgres.PASSWORD)) {
                // All days Table query
                String query = """ 
                    SELECT * 
                    FROM public.transaction
                    WHERE id = ? 
                    AND transaction_date >= CURRENT_DATE - INTERVAL '7 days'
                    ORDER BY transaction_no DESC, transaction_date DESC
                        """;
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, Login.userIDVALUE());

                ResultSet resultSet2 = statement.executeQuery();
                // Set the JTable's model to the non-editable model
                wklySpndngsTble.setModel(buildTableModel(resultSet2));
                // Apply the center renderer to all columns
                for (int i = 0; i < wklySpndngsTble.getColumnCount(); i++) {
                    wklySpndngsTble.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                }

                // monthly
                String query2 = """ 
                SELECT * 
                FROM public.transaction
                WHERE id = ?
                ORDER BY transaction_no DESC, transaction_date DESC
                    """;
                PreparedStatement statement1 = connection.prepareStatement(query2);
                statement1.setInt(1, Login.userIDVALUE());

                ResultSet resultSet = statement1.executeQuery();
                // Set the JTable's model to the non-editable model
                mnthlySpndingsTable.setModel(buildTableModel(resultSet));
                // Apply the center renderer to all columns
                for (int i = 0; i < mnthlySpndingsTable.getColumnCount(); i++) {
                    mnthlySpndingsTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else { // specific month 

            try (Connection connection = DriverManager.getConnection(postgres.DB_URL, postgres.USER, postgres.PASSWORD)) {
                // 7 days Table query
                String query2 = """ 
                    SELECT * 
                    FROM public.transaction
                    WHERE id = ? 
                    AND transaction_date >= CURRENT_DATE - INTERVAL '7 days'
                    AND EXTRACT(MONTH FROM transaction_date) = ? 
                    ORDER BY transaction_no DESC, transaction_date DESC
                        """;
                PreparedStatement statement = connection.prepareStatement(query2);
                statement.setInt(1, Login.userIDVALUE());
                statement.setInt(2, currentMonth);
    
                ResultSet resultSet2 = statement.executeQuery();
                // Set the JTable's model to the non-editable model
                wklySpndngsTble.setModel(buildTableModel(resultSet2));
                // Apply the center renderer to all columns
                for (int i = 0; i < wklySpndngsTble.getColumnCount(); i++) {
                    wklySpndngsTble.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                }
    
    
            } catch (SQLException e) {
                e.printStackTrace();
            }
    
            
            try (Connection connection = DriverManager.getConnection(postgres.DB_URL, postgres.USER, postgres.PASSWORD)) {
                // 30 days Table query
                String query = """ 
                    SELECT * 
                    FROM public.transaction
                    WHERE id = ? 
                    AND transaction_date >= CURRENT_DATE - INTERVAL '30 days'
                    AND EXTRACT(MONTH FROM transaction_date) = ? 
                    ORDER BY transaction_no DESC, transaction_date DESC
                        """;
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, Login.userIDVALUE());
                preparedStatement.setInt(2, currentMonth);
    
                ResultSet resultSet = preparedStatement.executeQuery();
                // Set the JTable's model to the non-editable model
                mnthlySpndingsTable.setModel(buildTableModel(resultSet));
                // Apply the center renderer to all columns
                for (int i = 0; i < mnthlySpndingsTable.getColumnCount(); i++) {
                    mnthlySpndingsTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                }
    
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }


    }

    // Method to disable single cell editable in my JTable
    public static DefaultTableModel buildNonEditableTableModel(ResultSet resultSet) throws SQLException {
        return new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };
    }

    // Method for occupying the table from the PostgreSQL
    public static DefaultTableModel buildTableModel(ResultSet resultSet) throws SQLException {
        
        ResultSetMetaData databaseData = resultSet.getMetaData();
    
        int columnCount = databaseData.getColumnCount();
        String[] originalColumnNames = new String[columnCount];
        displayColumnNames = new String[columnCount - 2]; // Number of columns to be hidden
    
        Map<String, String> columnMapping = new HashMap<>();
        columnMapping.put("transaction_id", "Transaction ID");
        columnMapping.put("transaction_date", "Date");
        columnMapping.put("transaction_type", "Income / Expense");
        columnMapping.put("transaction_category", "Category");
        columnMapping.put("transaction_notes", "Notes");
        columnMapping.put("money_type", "Type");
        columnMapping.put("transaction_amount", "Amount");
    
        int[] hiddenColumnIndices = {1,3}; // Indices of columns to be hidden
    
        int hiddenColumnsCount = hiddenColumnIndices.length;
    
        for (int i = 1, j = 0, hiddenCount = 0; i <= columnCount; i++) {
            originalColumnNames[i - 1] = databaseData.getColumnName(i);
            if (hiddenCount < hiddenColumnsCount && i == hiddenColumnIndices[hiddenCount]) {
                // Skip the hidden column
                hiddenCount++;
            } else {
                displayColumnNames[j++] = columnMapping.getOrDefault(originalColumnNames[i - 1], originalColumnNames[i - 1]);
            }
        }
    
        DefaultTableModel tableModel = new DefaultTableModel(displayColumnNames, 0) {
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
      // all date  
    public static void allDateTotal(){
        try (Connection connection = DriverManager.getConnection(postgres.DB_URL, postgres.USER, postgres.PASSWORD)){
            String querys = """
                    SELECT SUM(transaction_amount) AS total_expenses
                    FROM public.transaction
                    WHERE id = ?
                    AND transaction_type = 'EXPENSE';
                    """;
            PreparedStatement execute1 = connection.prepareStatement(querys);
            execute1.setInt(1, Login.userIDVALUE());
            ResultSet result = execute1.executeQuery();

            if (result.next()){
                int totalExpenses = result.getInt("total_expenses");
                System.out.println(totalExpenses);
                DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
                String stotal = decimalFormat.format(totalExpenses);
                
                weeklyExAmount.setText(stotal);
                monthlyExAmount.setText(stotal);
 
                
                
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // weekly
    public static void weeklyTotal(){
        try (Connection connection = DriverManager.getConnection(postgres.DB_URL, postgres.USER, postgres.PASSWORD)){
            String query = """
                    SELECT SUM(transaction_amount) AS total_expenses
                    FROM public.transaction
                    WHERE id = ?
                    AND transaction_date >= CURRENT_DATE - INTERVAL '7 days'
                    AND EXTRACT(MONTH FROM transaction_date) = ? 
                    AND transaction_type = 'EXPENSE';
                    """;
            PreparedStatement execute = connection.prepareStatement(query);
            execute.setInt(1, Login.userIDVALUE());
            execute.setInt(2, currentMonth);
            ResultSet result = execute.executeQuery();

            if (result.next()){
                BigDecimal totalExpenses = result.getBigDecimal("total_expenses");
                if (totalExpenses != null) {
                    DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
                    String total = decimalFormat.format(totalExpenses);
                    weeklyExAmount.setText(total);
                } else {
                    // Handle the case where totalExpenses is null, set an appropriate default or handle accordingly
                    weeklyExAmount.setText("N/A");  // You can set a default value or handle it based on your requirements
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    // monthly
    public static void monthtlyTotal(){
        try (Connection connection = DriverManager.getConnection(postgres.DB_URL, postgres.USER, postgres.PASSWORD)){
            String query = """
                    SELECT SUM(transaction_amount) AS total_expenses
                    FROM public.transaction
                    WHERE id = ?
                    AND transaction_date >= CURRENT_DATE - INTERVAL '30 days'
                    AND EXTRACT(MONTH FROM transaction_date) = ? 
                    AND transaction_type = 'EXPENSE';
                    """;
            PreparedStatement execute = connection.prepareStatement(query);
            execute.setInt(1, Login.userIDVALUE());
            execute.setInt(2, currentMonth);
            ResultSet result = execute.executeQuery();

            if (result.next()){
                BigDecimal totalExpenses = result.getBigDecimal("total_expenses");
                if (totalExpenses != null) {
                    DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
                    String total = decimalFormat.format(totalExpenses);
                    monthlyExAmount.setText(total);
                } else {
                    // Handle the case where totalExpenses is null, set an appropriate default or handle accordingly
                    monthlyExAmount.setText("N/A");  // You can set a default value or handle it based on your requirements
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 

    // For my Jtable Methods Mouse Listener and doubleclick handler
    public void weeklyTableSelected(MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            selectedRow = wklySpndngsTble.getSelectedRow();
            // Take the Transaction ID for query
            if (selectedRow != -1) { // Check if any row is selected
                model = (DefaultTableModel)wklySpndngsTble.getModel();
                selectedValue = (int) model.getValueAt(selectedRow, 0);

                // Checks if the transaction id that was selected is intead in the database then fill in the textfield of edit record automatically
                try (Connection connection = DriverManager.getConnection(postgres.DB_URL, postgres.USER, postgres.PASSWORD)){
                    // Query 1
                    String query = "SELECT * FROM public.transaction WHERE transaction_id = ?";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setInt(1, selectedValue); // Replace 1 with the actual user_id you want to retrieve

                    ResultSet resultSet = statement.executeQuery();

                    if (resultSet.next()){
                        amountValue = resultSet.getBigDecimal("transaction_amount");
                        labelType = resultSet.getString("transaction_type");
                        dates = resultSet.getDate("transaction_date");
                        myDate = new SimpleDateFormat("yyyy-MM-dd").format(dates);
                        category = resultSet.getString("transaction_category");
                        transacNotes = resultSet.getString("transaction_notes");
                        paymentType = resultSet.getString("money_type");
                        new EditRecord();

                    }else{
                        System.out.println("Didn't find anything");
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void monthlyTableSelected(MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            selectedRow = mnthlySpndingsTable.getSelectedRow();
            // Take the Transaction ID for query
            if (selectedRow != -1) { // Check if any row is selected
                model = (DefaultTableModel)mnthlySpndingsTable.getModel();
                selectedValue = (int) model.getValueAt(selectedRow, 0);

                try (Connection connection = DriverManager.getConnection(postgres.DB_URL, postgres.USER, postgres.PASSWORD)){
                    // Query 1
                    String query = "SELECT * FROM public.transaction WHERE transaction_id = ?";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setInt(1, selectedValue); // Replace 1 with the actual user_id you want to retrieve

                    ResultSet resultSet = statement.executeQuery();

                    if (resultSet.next()){
                        amountValue = resultSet.getBigDecimal("transaction_amount");
                        labelType = resultSet.getString("transaction_type");
                        dates = resultSet.getDate("transaction_date");
                        myDate = new SimpleDateFormat("yyyy-MM-dd").format(dates);
                        category = resultSet.getString("transaction_category");
                        transacNotes = resultSet.getString("transaction_notes");
                        paymentType = resultSet.getString("money_type");
                        new EditRecord();

                    }else{
                        System.out.println("Didn't find anything");
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    // current user label para mo show kinsa ang user nka login
    public static void currentUser(){
        try (Connection connection = DriverManager.getConnection(postgres.DB_URL, postgres.USER, postgres.PASSWORD)){
            String query = "SELECT fname, lname FROM public.client_users WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, Login.userIDVALUE());

            ResultSet result = statement.executeQuery();

            if (result.next()){
                String fname = result.getString("fname");
                String lname = result.getString("lname");
                userName.setText(fname + " " + lname);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Themes
    public void changeTheme(String theme) {

        try {

            switch (theme.toLowerCase()) {
                case "light":
                    UIManager.setLookAndFeel(new FlatMacLightLaf());
                    break;
                case "dark":
                    UIManager.setLookAndFeel(new FlatMacDarkLaf());
                    break;
                case "midnight blue":
                    UIManager.setLookAndFeel(new FlatGradiantoMidnightBlueIJTheme());
                    break;
                case "light owl":
                    UIManager.setLookAndFeel(new FlatLightOwlIJTheme());
                    break;
                case "material lighter":
                    UIManager.setLookAndFeel(new FlatMaterialLighterIJTheme());
                    break;
                case "arc dark":
                    UIManager.setLookAndFeel(new FlatArcDarkIJTheme());
                    break;
                case "solarized light":
                    UIManager.setLookAndFeel(new FlatSolarizedLightIJTheme());
                    break;
                case "deep ocean":
                    UIManager.setLookAndFeel(new FlatMaterialDeepOceanIJTheme());
                    break;   
                default:
                    // Handle unknown themes
                    break;
            }
            // Update UI
            if (Salarium.themeGrabbed == null){
                System.out.println("Updated UI successfully");
                try (Connection connection = DriverManager.getConnection(postgres.DB_URL, postgres.USER, postgres.PASSWORD)) {
                    String query = "UPDATE public.client_users SET pref_themes = ? WHERE id = ?";
                        try (PreparedStatement statement = connection.prepareStatement(query)){
                            statement.setString(1, theme);
                            statement.setInt(2, Login.userIDVALUE());
        
                            int rowsAffected = statement.executeUpdate();
                            if (rowsAffected > 0) {
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
                SwingUtilities.updateComponentTreeUI(this);
            } else {
                System.out.println("Set them automatically succeed");
                SwingUtilities.updateComponentTreeUI(this);
            }

        } catch (UnsupportedLookAndFeelException e) {
            System.err.println("Something went wrong in updating themes");
            e.printStackTrace();
        }
    }

    // Window Management Methods
    public static void disableRecBtn(){
        addRecord.setEnabled(false);
    }
    public static void enableRecBtn(){
        addRecord.setEnabled(true);
    }

    // Variables declaration - do not modify
    static javax.swing.JMenuItem fullData;
    static javax.swing.JMenuItem deepOcean;
    static javax.swing.JMenuItem solarized;
    static javax.swing.JMenuItem lightOwl;
    static javax.swing.JMenuItem materialLighter;
    static javax.swing.JMenuItem midNightBlue;
    private javax.swing.JMenu themesJMenu;
    static javax.swing.JMenuItem macOSlight;
    static javax.swing.JMenuItem macOSdark;
    static javax.swing.JLabel cashAmount; 
    private javax.swing.JScrollPane mnthlySpndngSP;
    static javax.swing.JTable mnthlySpndingsTable;
    private javax.swing.JScrollPane wklySpndngSP;
    static javax.swing.JTable wklySpndngsTble;
    static javax.swing.JButton addRecord;
    private javax.swing.JLabel copyRightLabel;
    private javax.swing.JLabel days30Lbl;
    private javax.swing.JLabel days7Lbl1;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel weeklyExLbl;
    static javax.swing.JLabel weeklyExAmount;
    static javax.swing.JLabel monthlyExAmount;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    static javax.swing.JLabel currentUserLogin;
    private javax.swing.JLabel jLabel7;
    static javax.swing.JLabel jLabel23;
    static javax.swing.JLabel userName;
    private javax.swing.JMenuItem logoutMenuItem;
    private javax.swing.JMenu mAccountMenu;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    static javax.swing.JComboBox<String> monthCB;
    private javax.swing.JLabel monthlySpendings;
    private javax.swing.JMenuItem newBalMenuItem;
    private javax.swing.JLabel notesLbl;
    private javax.swing.JMenu optionMenu;
    static javax.swing.JMenuItem arcDark;
    private javax.swing.JLabel profileLabel;
    private javax.swing.JMenuItem refreshMenuItem;
    private javax.swing.JMenuItem settingsMenuItem;
    private javax.swing.JLabel statisticsLBL;
    private javax.swing.JLabel weeklySpendings;
    private javax.swing.JComboBox<String> yearCB;
    // End of variables declaration                   
}
