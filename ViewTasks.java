package student_todo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ViewTasks extends JFrame {

    JTable table;
    DefaultTableModel model;
    JScrollPane scrollPane;

    ViewTasks() {
        setTitle("View Tasks");

        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Title");
        model.addColumn("Description");
        model.addColumn("Status");

        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 20, 550, 300);

        add(scrollPane);

        loadTasks();

        setSize(620, 400);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void loadTasks() {
        try {
            Connection con = DBConnection.getConnection();
            String query = "select task_id, title, description, status from tasks";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[] {
                        rs.getInt("task_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("status")
                });
            }

            rs.close();
            pst.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
