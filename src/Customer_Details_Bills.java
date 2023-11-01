import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Customer_Details_Bills {
    private JPanel customer;
    private JTable table;

    private JTextField dateC;

    private JButton s;
    private JButton ss;
    Connection connection = database_connection.connection();
    Statement statement = null;

    public Customer_Details_Bills() {
        JFrame frame = new JFrame("Customer_Details_Bills");
        frame.setContentPane(customer);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1100,600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        s.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    statement =connection.createStatement();
                    String date = dateC.getText();
                    String sql = "SELECT * FROM customers WHERE check_out = '"+date+"'";
                    ResultSet rs = statement.executeQuery(sql);
                    table.setModel(DbUtils.resultSetToTableModel(rs));

                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null,ex);
                }

            }
        });
        ss.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              frame.dispose();
            }
        });

    }
}
