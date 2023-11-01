import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Ckeck_In {
    private JTextField name;
    private JTextField mob;
    private JTextField nationality;
    private JComboBox gender;
    private JTextField email;
    private JTextField id;
    private JTextField address;
    private JTextField date;
    private JComboBox bed;
    private JTextField price;
    private JButton alloteRoomButton;
    private JButton clearButton;
    private JPanel customer;
    private JButton button1;
    private JComboBox type;
    private JComboBox room_number;
    Connection con = database_connection.connection();
    Statement statement = null;



    public Ckeck_In() {
        JFrame frame = new JFrame("ckeck_in");
        frame.setContentPane(customer);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);

        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy/MM/dd");
        Calendar calendar = Calendar.getInstance();
        date.setText(myFormat.format(calendar.getTime()));


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new Ckeck_In();
            }
        });
        bed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roomDetails();
            }
        });
        type.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roomDetails();
            }
        });
        room_number.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Room_n = (String) room_number.getSelectedItem();
                try {
                    statement = con.createStatement();
                    String sql = "SELECT * FROM room WHERE room_number = '" + Room_n + "'";
                  ResultSet  resultSet = statement.executeQuery(sql);

                    if (resultSet.next()) {
                        price.setText(resultSet.getString("price"));
                    }


                } catch (Exception E) {
                    JOptionPane.showMessageDialog(null, E);
                }
            }
        });

        alloteRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Name = name.getText();
                String phone = mob.getText();
                String nation = nationality.getText();
                String Gender = (String) gender.getSelectedItem();
                String mail = email.getText();
                String proof = id.getText();
                String Address = address.getText();
                String check_in = date.getText();
                String Bed = (String) bed.getSelectedItem();
                String room_nu = (String) room_number.getSelectedItem();
                String room_t = (String) type.getSelectedItem();
                String Price = price.getText();
                try {
                    statement = con.createStatement();
                    if (Name.equals("") || phone.equals("") || nation.equals("") || mail.equals("") || proof.equals("") || Address.equals("")|| price.equals("")) {
                        JOptionPane.showMessageDialog(null, "Fill Out All Items.!", "ERROR", JOptionPane.ERROR_MESSAGE);


                    } else if (!Price.equals("")) {
                        String sql = "UPDATE room SET status = 'Booked' WHERE room_number = '" + room_nu + "'";
                        statement.executeUpdate(sql);
                        String sqL = "INSERT INTO customers (name,mobile,nationality,gender,email,id_proof,adderss,check_in,bed,room_type,room_number,price_per_day) VALUES ('" + Name + "','" + phone + "','" + nation + "','" + Gender + "','" + mail + "','" + proof + "','" + Address + "','" + check_in + "','" + Bed + "','" + room_t + "','" + room_nu + "','" + Price + "') ";
                        statement.executeUpdate(sqL);
                        JOptionPane.showMessageDialog(null, "Customer Checked IN Successfully...!");
                        frame.setVisible(false);
                        new Ckeck_In();
                    }



                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        });
    }

    public void roomDetails() {

        room_number.removeAllItems();
        price.setText("");
        String Bed = (String) bed.getSelectedItem();
        String Room_T = (String) type.getSelectedItem();
        try {
           statement = con.createStatement();
           String sqL = "SELECT * FROM room WHERE bed = '"+Bed+"' AND room_type = '"+Room_T+"' AND status = 'Not Booked'";

         ResultSet  resultSet =  statement.executeQuery(sqL);
           while (resultSet.next()) {
               room_number.addItem(resultSet.getString(1));
           }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

}
