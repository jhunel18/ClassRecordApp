/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pupapps.classrecordapp;

import java.sql.SQLException;
import pupapps.classrecordapp.repository.Accounts;
import static pupapps.classrecordapp.repository.Accounts.createUsersTable;

/**
 *
 * @author lenovo
 */
public class UserManagement {
    RegisterForm registerForm = new RegisterForm();
    LoginForm loginForm = new LoginForm();
    public void showAppropriateForm() throws SQLException {
        createUsersTable(); // Ensure the table is created
        if (Accounts.doesTableExist("users") && !Accounts.isUserTableEmpty()) {
            loginForm.setVisible(true);
        } else {
            registerForm.setVisible(true);
        }
    }
}
  
