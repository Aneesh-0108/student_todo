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

public class UpdateTask extends JFrame implements ActionListener {

    JLabel l1, l2;
    JTextField taskIdField, statusField;
    JButton updateButton;

    UpdateTask() {
        setTitle("Update Task");

        l1 = new JLabel("Task ID");
        l1.setBounds(50, 50, 100, 30);

        taskIdField = new JTextField();
        taskIdField.setBounds(170, 50, 180, 30);

        l2 = new JLabel("New Status");
        l2.setBounds(50, 100, 100, 30);

        statusField = new JTextField();
        statusField.setBounds(170, 100, 180, 30);

        updateButton = new JButton("Update");
        updateButton.setBounds(150, 160, 100, 30);
        updateButton.addActionListener(this);

        add(l1);
        add(taskIdField);
        add(l2);
        add(statusField);
        add(updateButton);

        setSize(450, 280);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            try {
                Connection con = DBConnection.getConnection();
                String query = "update tasks set status = ? where task_id = ?";
                PreparedStatement pst = con.prepareStatement(query);

                pst.setString(1, statusField.getText());
                pst.setInt(2, Integer.parseInt(taskIdField.getText()));

                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Task updated successfully");

                pst.close();
                con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error updating task");
            }
        }
    }
}
