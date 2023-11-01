import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home {
    private JPanel home;
    private JButton manageRoomButton;
    private JButton customerCheckInButton;
    private JButton customerCheckOutButton;
    private JButton customerDetailsBillButton;
    private JButton logOutButton;
    private JButton exitButton;

    public Home() {

        JFrame frame = new JFrame("home");
        frame.setContentPane(home);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(1600,860);
        frame.setLocationRelativeTo(null);



        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a = JOptionPane.showConfirmDialog(null,"Do You Really want to logout?!","Log Out",JOptionPane.YES_NO_OPTION);

                if(a ==0){
                    frame.dispose();
                    new LogIn();

                }
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a = JOptionPane.showConfirmDialog(null,"Do You Really want to Close Application?!","Exit",JOptionPane.YES_NO_OPTION);

                if(a ==0){
                    System.exit(0);

                }
            }
        });
        manageRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Manage_Room();
            }
        });
        customerCheckInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Ckeck_In();
            }
        });
        customerCheckOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Check_Out();
            }
        });
        customerDetailsBillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Customer_Details_Bills();
            }
        });
    }


}
