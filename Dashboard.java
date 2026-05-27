package student_todo;

import java.awt.event.*;
import javax.swing.*;

public class Dashboard extends JFrame implements ActionListener {

    JButton b1, b2, b3, b4;

    Dashboard() {

        setTitle("Dashboard");

        b1 = new JButton("Add Task");
        b1.setBounds(100, 50, 150, 30);

        b2 = new JButton("View Tasks");
        b2.setBounds(100, 100, 150, 30);

        b3 = new JButton("Update Task");
        b3.setBounds(100, 150, 150, 30);

        b4 = new JButton("Delete Task");
        b4.setBounds(100, 200, 150, 30);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);

        add(b1);
        add(b2);
        add(b3);
        add(b4);

        setSize(400, 350);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == b1) {
            new AddTask();
        }

        if (e.getSource() == b2) {
            new ViewTasks();
        }

        if (e.getSource() == b3) {
            new UpdateTask();
        }

        if (e.getSource() == b4) {
            new DeleteTask();
        }
    }
}