import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;

public class SignUp {
    private JPanel signup;
    private JTextField name;
    private JTextField email;
    private JTextField answer;
    private JTextField adress;
    private JComboBox quition;
    private JButton signupButton;
    private JButton loginButton;
    private JTextField password;
    Connection connection = database_connection.connection();
    Statement statement = null;

    public SignUp() {
        JFrame frame = new JFrame("signup");
        frame.setContentPane(signup);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(850,768);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);




        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    statement = connection.createStatement();
                    String Name = name.getText();
                    String Email = email.getText();
                    String Pass = password.getText();
                    String security = (String) quition.getSelectedItem();
                    String Answer = answer.getText();
                    String Address = adress.getText();
                    String sql ="INSERt INTO account (Name,Email,Password,Security_Question,Answer,Address) VALUES('"+Name+"','"+Email+"','"+Pass+"','"+security+"','"+Answer+"','"+Address+"')";
                    if (Name.equals("") && Email.equals("") && Pass.equals("") && Answer.equals("")&& Address.equals("")){
                        JOptionPane.showMessageDialog(null,"Fill Out All Items","ERROR",JOptionPane.ERROR_MESSAGE);

                    }else {
                        statement.executeUpdate(sql);
                        JOptionPane.showMessageDialog(null,"Data Inserted Successfully","",JOptionPane.INFORMATION_MESSAGE);
                        clear();
                    }



                }catch (Exception exception){
                    JOptionPane.showMessageDialog(null,exception,"ERROR",JOptionPane.ERROR_MESSAGE);
                }

            }


            public void clear(){
                name.setText("");
                email.setText("");
                password.setText("");
                answer.setText("");
                adress.setText("");
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new LogIn();
            }
        });
    }
}
