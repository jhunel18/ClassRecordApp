package ClassRecord;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormRegister {
    JPanel registerPanel;
    private JTextField txtUsername;
    private JLabel lblUserPassword;
    private JLabel lblUsername;
    private JPasswordField txtPassword;
    private JButton registerButton;
public FormRegister() {
    registerButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

                String username = txtUsername.getText();
                String password = String.valueOf(txtPassword.getPassword());


                boolean isExist = DataHandler.registerUser(username,password);
                if(isExist){
                    JOptionPane.showMessageDialog(registerPanel, "Data Exists Already");
                }
                else {
                   JOptionPane.showMessageDialog(registerPanel, "Account created Successfully.");
                    DataHandler.registerUser(username,password);

                }
        }
    });
}
}
