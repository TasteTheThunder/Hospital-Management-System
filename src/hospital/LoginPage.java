package hospital;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

public class LoginPage extends javax.swing.JFrame {

    public LoginPage() {
        initComponents();
        this.getContentPane().setBackground(new Color(90, 90, 90));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        jButton1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jButton2.setCursor(new Cursor(Cursor.HAND_CURSOR));

        UIManager.put("OptionPane.background", new ColorUIResource(new Color(0, 128, 128)));
        UIManager.put("Panel.background", new ColorUIResource(new Color(0, 128, 128)));
        UIManager.put("OptionPane.messageForeground", new ColorUIResource(Color.WHITE));
        UIManager.put("Button.background", new ColorUIResource(Color.BLACK));
        UIManager.put("Button.foreground", new ColorUIResource(Color.RED));
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jLabel1 = new JLabel("Login Page ");
        jLabel2 = new JLabel("Username:");
        jLabel3 = new JLabel("Password:");
        user = new JTextField();
        pass = new JPasswordField();
        jButton1 = new JButton("LOGIN");
        jButton2 = new JButton("CLEAR");
        jLabel4 = new JLabel(new ImageIcon(getClass().getResource("/icons/login.png")));

        jLabel1.setFont(new Font("Cambria Math", Font.BOLD, 24));
        jLabel1.setForeground(Color.WHITE);

        jLabel2.setFont(new Font("Cambria", Font.BOLD, 14));
        jLabel2.setForeground(Color.WHITE);

        jLabel3.setFont(new Font("Cambria", Font.BOLD, 14));
        jLabel3.setForeground(Color.WHITE);

        jButton1.setBackground(new Color(54, 54, 54));
        jButton1.setFont(new Font("Cambria", Font.BOLD, 12));
        jButton1.setForeground(Color.WHITE);
        jButton1.addActionListener(evt -> loginAction());

        jButton2.setBackground(new Color(255, 51, 51));
        jButton2.setFont(new Font("Cambria", Font.BOLD, 12));
        jButton2.setForeground(Color.WHITE);
        jButton2.addActionListener(evt -> clearAction());

        pass.addActionListener(evt -> {}); // Optional

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(jLabel1)
                        .addComponent(jLabel4)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addComponent(user, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addComponent(pass, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addComponent(jButton2))
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addComponent(jLabel4)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(user, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(pass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton1)
                                .addComponent(jButton2))
        );

        pack();
    }

    private void loginAction() {
        String un = user.getText();
        String p = new String(pass.getPassword());

        if (un.isEmpty() || p.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both username and password");
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hms", "root", "abc3112@");
            Statement st = conn.createStatement();
            String sql = "select * from user_login";
            ResultSet rs = st.executeQuery(sql);

            boolean loginSuccess = false;
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");

                if (un.equals(username) && p.equals(password)) {
                    new welcome().setVisible(true);
                    this.setVisible(false);
                    loginSuccess = true;
                    break;
                }
            }
            if (!loginSuccess) {
                JOptionPane.showMessageDialog(this, "Wrong username or password");
            }

            rs.close();
            st.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error while establishing connection");
            e.printStackTrace();
        }
    }

    private void clearAction() {
        user.setText("");
        pass.setText("");
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new LoginPage().setVisible(true));
    }

    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField pass;
    private javax.swing.JTextField user;
}
