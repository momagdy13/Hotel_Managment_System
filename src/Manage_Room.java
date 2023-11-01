import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Manage_Room {
    private JPanel room;
    private JTable tablee;
    private JTextField room_n;
    private JComboBox type;
    private JComboBox bed;
    private JTextField price;
    private JButton addRoomButton;
    private JScrollPane table;
    private JButton exitButton;
    Connection Connection = database_connection.connection();
    Statement statement = null;

    public Manage_Room() {
        JFrame frame = new JFrame("manage_room");
        frame.setContentPane(room);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1100, 600);

        table.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                try {
                    statement = Connection.createStatement();
                    String sql = "SELECT * FROM room";
                    ResultSet rs = statement.executeQuery(sql);

                    tablee.setModel(DbUtils.resultSetToTableModel(rs));


                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, exception);
                }

            }
        });
        addRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    statement = Connection.createStatement();
                    String Room_n = room_n.getText();
                    String Room_t = (String) type.getSelectedItem();
                    String room_bed = (String) bed.getSelectedItem();
                    int Price = Integer.parseInt(price.getText());
                    String sql = "INSERT INTO room (room_number,room_type,bed,price,status) VALUES ('" + Room_n + "','" + Room_t + "','" + room_bed + "','" + Price + "','Not Booked')";
                    statement.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "Room Added Successfully..!");
                    frame.dispose();
                    new Manage_Room();

                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "Fill Out All Items!");
                }

            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              frame.dispose();
            }
        });
    }
}
