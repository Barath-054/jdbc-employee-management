package kodnest.jdbc.project;

import javax.swing.*;
import java.sql.*;

public class UpdateEmployee extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTextField idTf;
    private JTextArea detailsArea;
    private JTextField oldValueTf, newValueTf;
    private JLabel oldLabel, newLabel;
    private JButton submitBtn, updateBtn;

    private String selectedColumn = "";

    public UpdateEmployee() {

        setTitle("Update Employee");
        setBounds(100, 100, 600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel title = new JLabel("Update Employee", SwingConstants.CENTER);
        title.setBounds(200, 10, 200, 30);
        add(title);

        JLabel idLabel = new JLabel("Employee ID:");
        idLabel.setBounds(40, 60, 120, 25);
        add(idLabel);

        idTf = new JTextField();
        idTf.setBounds(180, 60, 200, 25);
        add(idTf);

        submitBtn = new JButton("Submit");
        submitBtn.setBounds(400, 60, 120, 25);
        add(submitBtn);

        // -------- Employee Details --------
        JLabel detailsLabel = new JLabel("Employee Details:");
        detailsLabel.setBounds(40, 100, 150, 25);
        add(detailsLabel);

        detailsArea = new JTextArea();
        detailsArea.setBounds(40, 130, 480, 100);
        detailsArea.setEditable(false);
        add(detailsArea);

        // -------- Old Value --------
        oldLabel = new JLabel("Old Value:");
        oldLabel.setBounds(40, 250, 140, 25);
        add(oldLabel);

        oldValueTf = new JTextField();
        oldValueTf.setBounds(180, 250, 200, 25);
        oldValueTf.setEditable(false);
        add(oldValueTf);

        // -------- New Value --------
        newLabel = new JLabel("New Value:");
        newLabel.setBounds(40, 290, 140, 25);
        add(newLabel);

        newValueTf = new JTextField();
        newValueTf.setBounds(180, 290, 200, 25);
        newValueTf.setEnabled(false);
        add(newValueTf);

        updateBtn = new JButton("Confirm Update");
        updateBtn.setBounds(400, 290, 140, 30);
        updateBtn.setEnabled(false);
        add(updateBtn);

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(40, 350, 100, 30);
        add(backBtn);

        // ================= SUBMIT =================
        submitBtn.addActionListener(e -> {

            detailsArea.setText("");
            oldValueTf.setText("");
            newValueTf.setText("");
            newValueTf.setEnabled(false);
            updateBtn.setEnabled(false);

            if (idTf.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter Employee ID");
                return;
            }

            try {
                int id = Integer.parseInt(idTf.getText());

                Connection con = DBUtil.getConnection();
                PreparedStatement ps =
                        con.prepareStatement("SELECT * FROM employee WHERE id=?");
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();

                if (!rs.next()) {
                    JOptionPane.showMessageDialog(this, "Employee not found");
                    return;
                }

                // Show ALL details
                detailsArea.setText(
                        "ID: " + rs.getInt("id") + "\n" +
                        "Name: " + rs.getString("name") + "\n" +
                        "Salary: " + rs.getDouble("salary") + "\n" +
                        "Department: " + rs.getString("department") + "\n" +
                        "Position: " + rs.getString("position")
                );

                // Ask what to update
                String[] options = { "Name", "Salary", "Department", "Position" };

                String choice = (String) JOptionPane.showInputDialog(
                        this,
                        "Which field do you want to update?",
                        "Select Field",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]
                );

                if (choice == null) return;

                selectedColumn = choice.toLowerCase();

                String oldValue = rs.getString(selectedColumn);

                oldLabel.setText("Old " + choice + ":");
                newLabel.setText("New " + choice + ":");
                oldValueTf.setText(oldValue);

                newValueTf.setEnabled(true);
                updateBtn.setEnabled(true);

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error occurred");
            }
        });

        // ================= CONFIRM UPDATE =================
        updateBtn.addActionListener(e -> {

            if (newValueTf.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter new value");
                return;
            }

            try {
                int id = Integer.parseInt(idTf.getText());
                String newValue = newValueTf.getText();

                String sql =
                        "UPDATE employee SET " + selectedColumn + "=? WHERE id=?";

                Connection con = DBUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);

                if (selectedColumn.equals("salary")) {
                    ps.setDouble(1, Double.parseDouble(newValue));
                } else {
                    ps.setString(1, newValue);
                }

                ps.setInt(2, id);
                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Employee updated successfully");

                oldValueTf.setText(newValue);
                newValueTf.setText("");

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // ================= BACK =================
        backBtn.addActionListener(e -> {
            new Home().setVisible(true);
            dispose();
        });
    }
}
