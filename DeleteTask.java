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

public class DeleteTask extends JFrame implements ActionListener {

    JLabel l1;
    JTextField taskIdField;
    JButton deleteButton;

    DeleteTask() {
        setTitle("Delete Task");

        l1 = new JLabel("Task ID");
        l1.setBounds(50, 50, 100, 30);

        taskIdField = new JTextField();
        taskIdField.setBounds(170, 50, 180, 30);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(150, 110, 100, 30);
        deleteButton.addActionListener(this);

        add(l1);
        add(taskIdField);
        add(deleteButton);

        setSize(450, 230);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteButton) {
            try {
                Connection con = DBConnection.getConnection();
                String query = "delete from tasks where task_id = ?";
                PreparedStatement pst = con.prepareStatement(query);

                pst.setInt(1, Integer.parseInt(taskIdField.getText()));

                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Task deleted successfully");

                pst.close();
                con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error deleting task");
            }
        }
    }
}
