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

public class viewdetailDoc extends javax.swing.JFrame {

    public viewdetailDoc() {
        initComponents();

        this.getContentPane().setBackground(new Color(90, 90, 90));
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        jButton2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jButton3.setCursor(new Cursor(Cursor.HAND_CURSOR));

        UIManager.put("OptionPane.background", new ColorUIResource(new Color(0, 128, 128)));
        UIManager.put("Panel.background", new ColorUIResource(new Color(0, 128, 128)));
        UIManager.put("OptionPane.messageForeground", new ColorUIResource(Color.WHITE));
        UIManager.put("Button.background", new ColorUIResource(Color.BLACK));
        UIManager.put("Button.foreground", new ColorUIResource(Color.RED));

        loadDoctorDetails(); // Load on page load
    }

    private void loadDoctorDetails() {
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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24));
        jLabel1.setText("VIEW DETAILS");
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {},
                new String [] {
                        "Id", "Doctor name", "Specialization"
                }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton2.setBackground(new java.awt.Color(54, 54, 54));
        jButton2.setFont(new java.awt.Font("Cambria", 1, 14));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("BACK");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DOCTORS obj = new DOCTORS();
                obj.setVisible(true);
                dispose();
            }
        });

        jButton3.setBackground(new java.awt.Color(158, 28, 28));
        jButton3.setFont(new java.awt.Font("Cambria", 1, 14));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("LOGOUT");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginPage obj = new LoginPage();
                obj.setVisible(true);
                dispose();
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(235, 235, 235)
                                                .addComponent(jLabel1))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(48, 48, 48)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(57, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(97, 97, 97)
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3)
                                .addGap(130, 130, 130))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton2)
                                        .addComponent(jButton3))
                                .addGap(19, 19, 19))
        );

        pack();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new viewdetailDoc().setVisible(true);
            }
        });
    }

    // Variables declaration
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
}
