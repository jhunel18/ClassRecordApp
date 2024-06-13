/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package pupapps.classrecordapp;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author lenovo
 */
public class ClassRecordApp {
    public static void main(String[] args)  {
       UserManagement userManagement = new UserManagement();
       
        try {
            userManagement.showAppropriateForm();
        } catch (SQLException ex) {
            Logger.getLogger(ClassRecordApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
