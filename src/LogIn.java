import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LogIn {
    private JPanel log;
    private JTextField email;
    private JPasswordField passwordField;
    private JCheckBox pass;
    private JButton spass;
    private JButton signUpButton;
    private JButton logInButton;




    public LogIn() {

        JFrame frame = new JFrame("login");
        frame.setContentPane(log);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setSize(850,700);

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new SignUp();
            }
        });
        pass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pass.isSelected()) {
                    passwordField.setEchoChar((char) 0);
                } else {


                    passwordField.setEchoChar('•');
                }
            }

        });
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (email.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please fill out Email", "MESSAGE", JOptionPane.ERROR_MESSAGE);

                } else if (passwordField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please fill out password", "MESSAGE", JOptionPane.ERROR_MESSAGE);

                } else {
                    PreparedStatement ps;
                    Connection connection = database_connection.connection();
                    try {
                        ps = connection.prepareStatement("SELECT * FROM account WHERE Email = ? AND Password = ?");
                        ps.setString(1, email.getText());
                        ps.setString(2, passwordField.getText());
                        ResultSet rs = ps.executeQuery();
                        if (rs.next()) {
                            frame.dispose();
                            new Home();


                        } else {
                            JOptionPane.showMessageDialog(null, "Email or Password invalid", "MESSAGE", JOptionPane.ERROR_MESSAGE);
                        }


                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }


                }
                }
        });
        spass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new ForgotPassword();
            }
        });

    }
}
