package ClassRecord;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerAdapter;
import java.sql.SQLException;
import java.util.Arrays;

public class FormLogin {
    private JPanel frmPanel;
    private JButton loginButton;
    private JButton btnRegister;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JTable table1;

    public FormLogin() {

    loginButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = txtUsername.getText();
            String password = String.valueOf(txtPassword.getPassword());
            boolean isValid = DataHandler.loginUser(username,password);
            if(isValid){
                JOptionPane.showMessageDialog(frmPanel,"Welcome");

            }
            else{
                JOptionPane.showMessageDialog(frmPanel, "Invalid user");
            }
        }
    });
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame frmRegister = new JFrame();
                frmRegister.setContentPane(new FormRegister().registerPanel);
                frmRegister.setSize(300,300);
                frmRegister.setVisible(true);

            }
        });
    }

    public static void main(String[] args) {
        JFrame f= new JFrame("Class Record System");
        f.setSize(200,300);
        f.setContentPane( new FormLogin().frmPanel);
        f.setVisible(true);
    }

}
