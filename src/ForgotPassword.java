import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ForgotPassword {
    private JPanel forgot;
    private JTextField email;
    private JTextField security;
    private JTextField answer;
    private JTextField password;
    private JButton logInButton;
    private JButton signUpButton;
    private JButton updateButton;
    private JButton searchButton;
    Connection connection = database_connection.connection();
    Statement statement = null;


    public ForgotPassword() {
        JFrame frame = new JFrame("forgotpassword");
        frame.setContentPane(forgot);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);


        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new SignUp();
            }
        });
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new LogIn();
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    statement = connection.createStatement();
                    String Email = email.getText();
                    String sql = "SELECT * FROM account WHERE Email = '" + Email + "' ";
                    ResultSet rs = statement.executeQuery(sql);
                    String Answer = answer.getText();

                    if (rs.next()) {
                        security.setText(rs.getString("Security_Question"));
                    } else {
                        JOptionPane.showMessageDialog(null, "Record Not Found", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }


                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, exception);
                }

            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String AN = answer.getText();
                String Password = password.getText();
                String Email = email.getText();
                String SEC = security.getText();


                try {
                    String  Sql= "SELECT * FROM account WHERE Email = '" + Email + "'AND Security_Question = '"+SEC+"'AND Answer = '"+AN+"' ";
                    ResultSet resultSet = statement.executeQuery(Sql);
                    if (resultSet.next() && !Password.equals("")){
                        String sql = "UPDATE account SET Password = '" + Password + "' WHERE  Answer = '" + AN + "' ";
                        statement.executeUpdate(sql);
                        JOptionPane.showMessageDialog(null, "Password Change Successfully!");
                        clear();
                    }else {
                        JOptionPane.showMessageDialog(null,"Incorrect Answer!\n OR Fill Out Password!","ERROR",JOptionPane.ERROR_MESSAGE);

                    }




                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "Fill out All Items!", "ERROR", JOptionPane.ERROR_MESSAGE);

                }
            }
        });
    }

    public void clear() {

        email.setText("");
        password.setText("");
        answer.setText("");
        security.setText("");

    }
}
