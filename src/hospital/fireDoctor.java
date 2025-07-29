package hospital;

import java.awt.Color;
import java.awt.Cursor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;

public class fireDoctor extends javax.swing.JFrame {

    public fireDoctor() {
        initComponents();
        this.getContentPane().setBackground(new Color(90, 90, 90));
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        fire.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logout.setCursor(new Cursor(Cursor.HAND_CURSOR));

        UIManager.put("OptionPane.background", new ColorUIResource(new Color(0, 128, 128)));
        UIManager.put("Panel.background", new ColorUIResource(new Color(0, 128, 128)));
        UIManager.put("OptionPane.messageForeground", new ColorUIResource(Color.WHITE));
        UIManager.put("Button.background", new ColorUIResource(Color.BLACK));
        UIManager.put("Button.foreground", new ColorUIResource(Color.RED));

        loadDoctorRecords(); // Automatically load records
    }

    private void loadDoctorRecords() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root", "abc3112@");

            String sql = "SELECT * FROM doctor_record";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            DefaultTableModel tm = (DefaultTableModel) jTable1.getModel();
            tm.setRowCount(0);

            while (rs.next()) {
                Object[] row = {
                        rs.getInt("ID"),
                        rs.getString("DOCTORNAME"),
                        rs.getString("SPECIALIZATION")
                };
                tm.addRow(row);
            }

            rs.close();
            pstmt.close();
            conn.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error loading records: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        fd = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        fire = new javax.swing.JButton();
        back = new javax.swing.JButton();
        logout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24));
        jLabel1.setText("Fire Doctor");
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {},
                new String [] { "Id", "Doctor Name", "Specialization" }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setText("To Fire the doctor, Write his Id :");
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));

        fire.setBackground(new java.awt.Color(158, 28, 28));
        fire.setFont(new java.awt.Font("Cambria", 1, 14));
        fire.setForeground(new java.awt.Color(255, 255, 255));
        fire.setText("Fire");
        fire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fireActionPerformed(evt);
            }
        });

        back.setBackground(new java.awt.Color(54, 54, 54));
        back.setFont(new java.awt.Font("Cambria", 1, 14));
        back.setForeground(new java.awt.Color(255, 255, 255));
        back.setText("Back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        logout.setBackground(new java.awt.Color(158, 28, 28));
        logout.setFont(new java.awt.Font("Cambria", 1, 14));
        logout.setForeground(new java.awt.Color(255, 255, 255));
        logout.setText("Logout");
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });

        // Layout code
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup().addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel1)
                                                        .addComponent(jLabel2)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(fd, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(fire)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 190, Short.MAX_VALUE)
                                                                .addComponent(back)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(logout)))
                                                .addContainerGap())))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup().addGap(18, 18, 18)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(fd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(fire)
                                        .addComponent(back)
                                        .addComponent(logout))
                                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }

    private void fireActionPerformed(java.awt.event.ActionEvent evt) {
        String fid = fd.getText().trim();
        if (fid.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter an ID.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                null,
                "Are you sure you want to fire the doctor?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root", "abc3112@");

                String checkSql = "SELECT * FROM doctor_record WHERE id = ?";
                PreparedStatement checkStmt = conn.prepareStatement(checkSql);
                checkStmt.setString(1, fid);
                ResultSet rs = checkStmt.executeQuery();

                if (!rs.next()) {
                    JOptionPane.showMessageDialog(null, "ID not found in Database");
                } else {
                    String deleteSql = "DELETE FROM doctor_record WHERE id = ?";
                    try (PreparedStatement deleteStmt = conn.prepareStatement(deleteSql)) {
                        deleteStmt.setString(1, fid);
                        deleteStmt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Doctor fired successfully.");
                        fd.setText("");
                        loadDoctorRecords(); // Refresh table
                    }
                }

                rs.close();
                checkStmt.close();
                conn.close();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error firing doctor: " + e.getMessage());
            }
        }
    }

    private void backActionPerformed(java.awt.event.ActionEvent evt) {
        DOCTORS obj = new DOCTORS();
        obj.setVisible(true);
        dispose();
    }

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {
        LoginPage obj = new LoginPage();
        obj.setVisible(true);
        dispose();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new fireDoctor().setVisible(true);
        });
    }

    // Variables declaration
    private javax.swing.JButton back;
    private javax.swing.JTextField fd;
    private javax.swing.JButton fire;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton logout;
}
