package kodnest.jdbc.project;

import javax.swing.*;
import java.awt.Font;

public class EmployeeOnboarding extends JFrame {

	private static final long serialVersionUID = 1L;

	
    JTextField tf1, tf2, tf3, tf4;

    public EmployeeOnboarding() {
        setBounds(100,100,450,320);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel title = new JLabel("Employee Onboarding", SwingConstants.CENTER);
        title.setBounds(120,10,200,30);
        add(title);

        addLabel("Name",25,50);
        addLabel("Salary",25,100);
        addLabel("Department",25,150);
        addLabel("Position",25,200);

        tf1 = addText(130,50);
        tf2 = addText(130,100);
        tf3 = addText(130,150);
        tf4 = addText(130,200);

        JButton add = new JButton("Onboard");
        add.setBounds(300,240,100,30);
        add(add);

        JButton back = new JButton("Back");
        back.setBounds(40,240,100,30);
        add(back);

        add.addActionListener(e -> {
            try {
                var con = DBUtil.getConnection();
                var ps = con.prepareStatement(
                    "INSERT INTO employee(name,salary,department,position) VALUES(?,?,?,?)");
                ps.setString(1, tf1.getText());
                ps.setDouble(2, Double.parseDouble(tf2.getText()));
                ps.setString(3, tf3.getText());
                ps.setString(4, tf4.getText());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this,"Employee Added");
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        });

        back.addActionListener(e -> { new Home().setVisible(true); dispose(); });
    }

    void addLabel(String t,int x,int y){
        JLabel l=new JLabel(t);
        l.setBounds(x,y,100,30);
        add(l);
    }
    JTextField addText(int x,int y){
        JTextField t=new JTextField();
        t.setBounds(x,y,200,30);
        add(t);
        return t;
    }
}
