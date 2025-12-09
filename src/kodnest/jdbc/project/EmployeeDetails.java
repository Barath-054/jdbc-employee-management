package kodnest.jdbc.project;

import javax.swing.*;

public class EmployeeDetails extends JFrame {

	private static final long serialVersionUID = 1L;

	
    JTextField idField, result;

    public EmployeeDetails() {
        setBounds(100,100,450,300);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel title=new JLabel("Employee Details",SwingConstants.CENTER);
        title.setBounds(120,10,200,30);
        add(title);

        JLabel l=new JLabel("Emp ID");
        l.setBounds(30,80,100,30);
        add(l);

        idField=new JTextField();
        idField.setBounds(140,80,200,30);
        add(idField);

        JButton search=new JButton("Search");
        search.setBounds(170,120,90,30);
        add(search);

        result=new JTextField();
        result.setBounds(30,170,380,40);
        add(result);

        JButton back=new JButton("Back");
        back.setBounds(30,230,80,30);
        add(back);

        search.addActionListener(e -> {
            try {
                var con = DBUtil.getConnection();
                var ps = con.prepareStatement("SELECT * FROM employee WHERE id=?");
                ps.setInt(1,Integer.parseInt(idField.getText()));
                var rs=ps.executeQuery();
                if(rs.next()){
                    result.setText(
                      rs.getString("name")+" | "+rs.getDouble("salary")+" | "+
                      rs.getString("department")+" | "+rs.getString("position"));
                } else result.setText("Employee Not Found");
            } catch(Exception ex){ ex.printStackTrace(); }
        });

        back.addActionListener(e -> { new Home().setVisible(true); dispose(); });
    }
}
