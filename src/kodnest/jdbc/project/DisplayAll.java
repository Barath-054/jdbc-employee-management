package kodnest.jdbc.project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class DisplayAll extends JFrame {

    private static final long serialVersionUID = 1L;

    JTable table;
    DefaultTableModel model;

    public DisplayAll() {

        setTitle("Employee Table");
        setBounds(100, 100, 700, 400);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel("Employee Table", SwingConstants.CENTER);
        title.setBounds(250, 10, 200, 30);
        add(title);

        // -------- Table Model (Column Names) --------
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Salary");
        model.addColumn("Department");
        model.addColumn("Position");

        table = new JTable(model);
        table.setEnabled(false); // read-only table

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 60, 650, 250);
        add(scrollPane);

        JButton back = new JButton("Back");
        back.setBounds(20, 330, 100, 30);
        add(back);

        // -------- Load Data from DB --------
        loadEmployeeData();

        back.addActionListener(e -> {
            new Home().setVisible(true);
            dispose();
        });
    }

    // -------- JDBC DATA FETCH METHOD --------
    private void loadEmployeeData() {

        try {
            Connection con = DBUtil.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM employee");

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("salary"),
                        rs.getString("department"),
                        rs.getString("position")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
