package kodnest.jdbc.project;

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.Font;

public class Home extends JFrame {

	private static final long serialVersionUID = 1L;

	
    public Home() {
        setTitle("Employee Editor");
        setBounds(100, 100, 450, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel title = new JLabel("Employee Editor", SwingConstants.CENTER);
        title.setFont(new Font("HP Simplified", Font.PLAIN, 16));
        title.setBounds(120, 10, 200, 30);
        add(title);

        JButton b1 = new JButton("View Employee Table");
        b1.setBounds(30, 70, 180, 35);
        add(b1);

        JButton b2 = new JButton("View Employee");
        b2.setBounds(240, 70, 160, 35);
        add(b2);

        JButton b3 = new JButton("Add Employee");
        b3.setBounds(30, 130, 180, 35);
        add(b3);

        JButton b4 = new JButton("Delete Employee");
        b4.setBounds(240, 130, 160, 35);
        add(b4);

        JButton b5 = new JButton("Update Employee");
        b5.setBounds(135, 190, 180, 35);
        add(b5);

        b1.addActionListener(e -> { new DisplayAll().setVisible(true); dispose(); });
        b2.addActionListener(e -> { new EmployeeDetails().setVisible(true); dispose(); });
        b3.addActionListener(e -> { new EmployeeOnboarding().setVisible(true); dispose(); });
        b4.addActionListener(e -> { new DeleteEmployee().setVisible(true); dispose(); });
        b5.addActionListener(e -> { new UpdateEmployee().setVisible(true); dispose(); });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new Home().setVisible(true));
    }
}
