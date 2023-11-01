import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class Check_Out {
    private JPanel check;
    private JTextField name;
    private JTextField pricee;
    private JTextField roomn;
    private JButton searchButton;
    private JButton check_OutButton;
    private JButton clearButton;
    private JTable table;
    private JTextField checkin;
    private JTextField date;
    private JTextField mob;
    private JTextField n_o_d;
    private JTextField amount;
    private JTextField email;
    private JScrollPane tablee;
    private JButton button1;
    private JTextField id;
    Connection con = database_connection.connection();
    Statement statement = null;


    public Check_Out() {
        JFrame frame = new JFrame("Check_Out");
        frame.setContentPane(check);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1500, 600);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        Show_Record();


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    statement = con.createStatement();

                    SimpleDateFormat myFormat = new SimpleDateFormat("yyyy/MM/dd");
                    Calendar calendar = Calendar.getInstance();
                    date.setText(myFormat.format(calendar.getTime()));
                    String room_n = roomn.getText();


                    String sql = "SELECT * FROM customers WHERE room_number = '" + room_n + "' AND check_out IS NULL";
                    ResultSet rs = statement.executeQuery(sql);

                    if (rs.next()) {
                        name.setText(rs.getString(2));
                        pricee.setText(rs.getString(13));
                        checkin.setText(rs.getString(9));
                        mob.setText(rs.getString(3));
                        email.setText(rs.getString(6));
                        id.setText(rs.getString("ID"));

                        String dateBefore = rs.getString("check_in");
                        Date dateB = myFormat.parse(dateBefore);
                        String dateAfter = myFormat.format(calendar.getTime());
                        Date dateA = myFormat.parse(dateAfter);
                        long difference = dateB.getTime() - dateA.getTime();
                        int noOfDayS = (int) (difference / (1000 * 60 * 60 * 24));
                        if (noOfDayS == 0) {
                            noOfDayS = 1;

                            n_o_d.setText(String.valueOf(noOfDayS));
                            float price = Float.parseFloat(pricee.getText());
                            amount.setText(String.valueOf(noOfDayS * price));
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Room Number  Isn't Booked or Doesn't Exist ", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, exception);
                }
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new Check_Out();
            }
        });
        check_OutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Name = name.getText();
                String Id = id.getText();
                String RN = roomn.getText();
                String Mob = mob.getText();
                String Email = email.getText();
                String NOD = n_o_d.getText();
                String Amount = amount.getText();
                try {
                    statement = con.createStatement();

                    String sql = "SELECT * FROM customers ";
                    ResultSet RS = statement.executeQuery(sql);
                    String SQL = "UPDATE customers SET number_of_day_stays = '" + n_o_d.getText() + "' , total_amount = '" + amount.getText() + "',check_out = '" + date.getText() + "' WHERE room_number = '" + RN + "'";
                    String SQl = "UPDATE room SET status = 'Not Booked' WHERE room_number = '" + RN + "'";
                    statement.executeUpdate(SQL);
                    statement.executeUpdate(SQl);

                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, exception);
                }



                JOptionPane.showMessageDialog(null, "Check Out Successfully....! ");
                frame.setVisible(false);
                new Check_Out();

            }
        });
    }

    public void Show_Record() {
        try {
            statement = con.createStatement();
            String SQL = "SELECT * FROM customers WHERE check_out IS NULL ";
            ResultSet resultSet = statement.executeQuery(SQL);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception);
        }
    }

   /* public void printBill() {
        String path = "D:\\";
        var doc = new Document();
        try {


          PdfWriter.getInstance(doc,new FileOutputStream(path+""+id.getText()+".pdf"));
          doc.open();
          Paragraph paragraph = new Paragraph("*********************************************************************************************************************************************************************************************");
          Paragraph paragraph1 = new Paragraph("                                                                                                                                                              MO Hotel Management System\n");
          doc.add(paragraph1);
          doc.add(paragraph);
          Paragraph paragraph2 = new Paragraph("\t ID: "+id.getText()+"\nCustomer Details:\nName: "+name.getText()+"\nMobile Number: "+mob.getText()+"\nEmail:  "+email.getText()+" ");
          doc.add(paragraph2);
          doc.add(paragraph);
          Paragraph paragraph3 = new Paragraph("Room Details:\nNumber: "+roomn.getText()+"");
          doc.add(paragraph3);
          doc.add(paragraph);
          com.itextpdf.text.pdf.PdfPTable pdfPTable = new PdfPTable(4);
          pdfPTable.addCell("Check IN Date:"+checkin.getText());
          pdfPTable.addCell("Check Out Date:" +date.getText());
          pdfPTable.addCell("Number Of Day Stays:"+n_o_d.getText());
          pdfPTable.addCell("Total Amount Paid:"+pricee.getText());
          doc.add(pdfPTable);
          doc.add(paragraph);
          Paragraph paragraph4 = new Paragraph("Thank you ,Please Visit Again..");
          doc.add(paragraph4);



        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        int a = JOptionPane.showConfirmDialog(null,"Do You Want to print Bill...!","Select",JOptionPane.YES_NO_OPTION);
        if (a==0){
            try {
                new File("D:\\"+id.getText()+".pdf");




            }catch (Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
        }
    } */

}
