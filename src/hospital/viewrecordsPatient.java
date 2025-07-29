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

public class viewrecordsPatient extends javax.swing.JFrame {

    public viewrecordsPatient() {
        initComponents();

        this.getContentPane().setBackground(new Color(90, 90, 90));
        // Center the frame
        this.setLocationRelativeTo(null);
        // do not resize
        this.setResizable(false);

        jButton2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jButton3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // Joptionpane styling
        UIManager.put("OptionPane.background", new ColorUIResource(new Color(0, 128, 128))); // Surgical green
        UIManager.put("Panel.background", new ColorUIResource(new Color(0, 128, 128)));

        // Set the text color to white
        UIManager.put("OptionPane.messageForeground", new ColorUIResource(Color.WHITE));

        // Customize the "OK" button
        UIManager.put("Button.background", new ColorUIResource(Color.BLACK));
        UIManager.put("Button.foreground", new ColorUIResource(Color.RED));

        loadPatientRecords();  // Load data immediately when frame opens
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
                        rs.getInt("id"),
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

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("VIEW DETAILS");
        jLabel1.setForeground(new java.awt.Color(225,225,225));

        jButton2.setBackground(new java.awt.Color(54, 54, 54));
        jButton2.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("BACK");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(156, 28, 28));
        jButton3.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("LOGOUT");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {},
                new String [] {
                        "ID", "Patient Name", "Disease", "Date"
                }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jButton2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 254, Short.MAX_VALUE)
                                                .addComponent(jButton3))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 184, Short.MAX_VALUE)
                                                .addComponent(jLabel1)
                                                .addGap(170, 170, 170)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton3)
                                        .addComponent(jButton2))
                                .addContainerGap(54, Short.MAX_VALUE))
        );

        pack();
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        PATIENT obj = new PATIENT();
        obj.setVisible(true);
        dispose();
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        LoginPage obj = new LoginPage();
        obj.setVisible(true);
        dispose();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new viewrecordsPatient().setVisible(true);
        });
    }

    // Variables declaration
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
}
