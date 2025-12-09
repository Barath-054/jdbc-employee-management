package kodnest.jdbc.project;

import javax.swing.*;

public class DeleteEmployee extends JFrame {

	private static final long serialVersionUID = 1L;

	
    JTextField id;

    public DeleteEmployee() {
        setBounds(100,100,450,300);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel l=new JLabel("Employee ID");
        l.setBounds(40,80,120,30);
        add(l);

        id=new JTextField();
        id.setBounds(160,80,150,30);
        add(id);

        JButton del=new JButton("Delete");
        del.setBounds(330,80,80,30);
        add(del);

        JButton back=new JButton("Back");
        back.setBounds(30,230,80,30);
        add(back);

        del.addActionListener(e -> {
            try{
                var con=DBUtil.getConnection();
                var ps=con.prepareStatement("DELETE FROM employee WHERE id=?");
                ps.setInt(1,Integer.parseInt(id.getText()));
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this,"Deleted");
            }catch(Exception ex){ex.printStackTrace();}
        });

        back.addActionListener(e -> { new Home().setVisible(true); dispose(); });
    }
}
