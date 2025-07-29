package hospital;

import java.awt.Color;
import java.awt.Cursor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;

public class dischargePatient extends javax.swing.JFrame {

    public dischargePatient() {
        initComponents();
        this.getContentPane().setBackground(new Color(90, 90, 90));
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        jButton2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jButton3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jButton4.setCursor(new Cursor(Cursor.HAND_CURSOR));

        UIManager.put("OptionPane.background", new ColorUIResource(new Color(0, 128, 128)));
        UIManager.put("Panel.background", new ColorUIResource(new Color(0, 128, 128)));
        UIManager.put("OptionPane.messageForeground", new ColorUIResource(Color.WHITE));
        UIManager.put("Button.background", new ColorUIResource(Color.BLACK));
        UIManager.put("Button.foreground", new ColorUIResource(Color.RED));

        loadPatientRecords(); // Load records on startup
    }

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        pd = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("Discharge Patient");
        jLabel1.setForeground(Color.WHITE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {},
                new String [] {
                        "ID", "Patient Name", "Disease", "Date"
                }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setText("To Discharge the patient, Write their ID:");
        jLabel2.setForeground(Color.WHITE);

        jButton2.setBackground(new java.awt.Color(158, 28, 28));
        jButton2.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Discharge");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(121, 121, 121));
        jButton3.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Back");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(158, 28, 28));
        jButton4.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Logout");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(pd, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(32, 32, 32)
                                                .addComponent(jButton2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                                                .addComponent(jButton3)
                                                .addGap(42, 42, 42)
                                                .addComponent(jButton4))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(230, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addGap(199, 199, 199))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton4)
                                        .addComponent(jButton3)
                                        .addComponent(jButton2)
                                        .addComponent(pd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }

    private void loadPatientRecords() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root", "abc3112@");
            String sql = "SELECT * FROM patient_record";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            DefaultTableModel tm = (DefaultTableModel) jTable1.getModel();
            tm.setRowCount(0);
            while (rs.next()) {
                Object o[] = {
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getString("Disease"),
                        rs.getString("Date")
                };
                tm.addRow(o);
            }
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        String pid = pd.getText().trim();
        if (pid.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter an ID.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                null,
                "Are you sure you want to discharge the patient?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root", "abc3112@")) {
                    String checkSql = "SELECT * FROM patient_record WHERE id = ?";
                    PreparedStatement checkStmt = conn.prepareStatement(checkSql);
                    checkStmt.setString(1, pid);
                    ResultSet rs = checkStmt.executeQuery();

                    if (!rs.next()) {
                        JOptionPane.showMessageDialog(null, "ID not found in Database");
                    } else {
                        String deleteSql = "DELETE FROM patient_record WHERE id = ?";
                        try (PreparedStatement deleteStmt = conn.prepareStatement(deleteSql)) {
                            deleteStmt.setString(1, pid);
                            deleteStmt.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Patient discharged successfully.");
                            pd.setText("");
                            loadPatientRecords(); // Refresh table after deletion
                        }
                    }
                    rs.close();
                    checkStmt.close();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        PATIENT obj = new PATIENT();
        obj.setVisible(true);
        dispose();
    }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        LoginPage obj = new LoginPage();
        obj.setVisible(true);
        dispose();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new dischargePatient().setVisible(true);
            }
        });
    }

    // Variables declaration
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField pd;
}
