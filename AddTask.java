package student_todo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AddTask extends JFrame implements ActionListener {

    JLabel l1, l2, l3;
    JTextField titleField, descriptionField, statusField;
    JButton addButton;

    AddTask() {
        setTitle("Add Task");

        l1 = new JLabel("Title");
        l1.setBounds(50, 50, 100, 30);

        titleField = new JTextField();
        titleField.setBounds(170, 50, 180, 30);

        l2 = new JLabel("Description");
        l2.setBounds(50, 100, 100, 30);

        descriptionField = new JTextField();
        descriptionField.setBounds(170, 100, 180, 30);

        l3 = new JLabel("Status");
        l3.setBounds(50, 150, 100, 30);

        statusField = new JTextField();
        statusField.setBounds(170, 150, 180, 30);

        addButton = new JButton("Add");
        addButton.setBounds(150, 210, 100, 30);
        addButton.addActionListener(this);

        add(l1);
        add(titleField);
        add(l2);
        add(descriptionField);
        add(l3);
        add(statusField);
        add(addButton);

        setSize(450, 330);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            try {
                Connection con = DBConnection.getConnection();
                String query = "insert into tasks (title, description, status) values (?, ?, ?)";
                PreparedStatement pst = con.prepareStatement(query);

                pst.setString(1, titleField.getText());
                pst.setString(2, descriptionField.getText());
                pst.setString(3, statusField.getText());

                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Task added successfully");

                pst.close();
                con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error adding task");
            }
        }
    }
}
