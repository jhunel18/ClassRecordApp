/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pupapps.classrecordapp.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



/**
 *
 * @author lenovo
 */
public class DbConnector {
   private static final String DB_URL = "jdbc:postgresql://localhost:5432/class-record-db";
   private static final String USER = "postgres";
   private static final String PASS = "pwd";


   public Connection getConnection() throws SQLException {
       return DriverManager.getConnection(DB_URL, USER, PASS);
   }

}
