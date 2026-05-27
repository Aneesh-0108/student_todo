package student_todo;

import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class LoginFrame extends JFrame implements ActionListener {

    JLabel l1, l2;
    JTextField tf1;
    JPasswordField pf1;
    JButton b1;

    LoginFrame() {

        setTitle("Login");

        l1 = new JLabel("Username");
        l1.setBounds(50, 50, 100, 30);

        tf1 = new JTextField();
        tf1.setBounds(150, 50, 150, 30);

        l2 = new JLabel("Password");
        l2.setBounds(50, 100, 100, 30);

        pf1 = new JPasswordField();
        pf1.setBounds(150, 100, 150, 30);

        b1 = new JButton("Login");
        b1.setBounds(120, 160, 100, 30);

        b1.addActionListener(this);

        add(l1);
        add(tf1);
        add(l2);
        add(pf1);
        add(b1);

        setSize(400, 300);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {

        String username = tf1.getText();
        String password = pf1.getText();

        try {

            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM users WHERE username=? AND password=?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                JOptionPane.showMessageDialog(this, "Login Successful");

                new Dashboard();

                dispose();

            } else {

                JOptionPane.showMessageDialog(this, "Invalid Login");

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new LoginFrame();
    }
}