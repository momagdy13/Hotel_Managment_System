import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Admin {
    private JPanel admin;
    private JButton logOutButton;
    private JTextField email;
    private JButton searchButton;
    private JButton clearButton;
    private JTable tablee;
    private JButton exit;
    private JScrollPane table;
    Connection connection = database_connection.connection();
    Statement statement = null;

    public Admin() {
        JFrame frame = new JFrame("admin");
        frame.setContentPane(admin);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(1600,850);
        frame.setLocationRelativeTo(null);



        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new LogIn();
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a = JOptionPane.showConfirmDialog(null,"Do You Want To Logout!","Log Out",JOptionPane.YES_NO_OPTION);
                if (a==0){
                    System.exit(0);
                }
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                email.setText("");
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    statement = connection.createStatement();
                    String Email = email.getText();
                    String Name = email.getText();
                    String sql = "SELECT * FROM account WHERE Email = '" + Email + "' OR Name = '"+Name+"' ";
                    ResultSet rs = statement.executeQuery(sql);
                    tablee.setModel(DbUtils.resultSetToTableModel(rs));


                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "Record Not Found", "ERROR", JOptionPane.ERROR_MESSAGE);

                    }


                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, exception);
                }

            }

        });
    }


}
