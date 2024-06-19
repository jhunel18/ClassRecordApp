/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pupapps.classrecordapp.repository;

import org.mindrot.jbcrypt.BCrypt;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author lenovo
 */
public class Accounts {
    private static DbConnector dbConnector;

    public Accounts(DbConnector dbConnector) {
        Accounts.dbConnector = dbConnector;
    }
    //Method for Creating User's Table
    public static void createUsersTable() throws SQLException {
        String sql = """
                     CREATE TABLE IF NOT EXISTS users (
                         id SERIAL PRIMARY KEY,
                         fname VARCHAR(50) NOT NULL,
                         lname VARCHAR(50) NOT NULL,
                         username VARCHAR(50) UNIQUE NOT NULL,
                         password VARCHAR(255) NOT NULL
                     )""";
        try (Connection conn = dbConnector.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
            throw e;  // Rethrow exception to ensure calling code is aware of the failure
        }
    }
    public static boolean doesTableExist(String tableName) throws SQLException {
        try (Connection conn = dbConnector.getConnection()) {
            DatabaseMetaData metaData = conn.getMetaData();
            try (ResultSet rs = metaData.getTables(null, null, tableName, null)) {
                return rs.next();
            }
        }
    }
    
    public static boolean isUserTableEmpty() throws SQLException {
        String sql = "SELECT COUNT(*) AS rowcount FROM users";
        try (Connection conn = dbConnector.getConnection(); 
             Statement stmt = conn.createStatement(); 
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt("rowcount") == 0;
            }
        }
        return true; // If there is an error, assume the table is empty
    }
    
    //Method for Inserting User to DB
     public void insertUser(String fname, String lname, String username, String password) throws SQLException {
        String insertQuery = "INSERT INTO users (fname, lname, username, password) VALUES (?, ?, ?, ?)";
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());  // Hash the password
        try (Connection conn = dbConnector.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {

            pstmt.setString(1, fname);
            pstmt.setString(2, lname);
            pstmt.setString(3, username);
            pstmt.setString(4, hashedPassword);
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Account successfully created");
        } catch (SQLException e) {
            System.out.println("Error inserting user: " + e.getMessage());
            throw e;  // Rethrow exception to ensure calling code is aware of the failure
        }
    }
     
    //Method for login user
    public boolean loginUser(String username, String password) throws SQLException{
         String loginQuery = "SELECT password FROM users WHERE username = ?";
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(loginQuery)) {

            pstmt.setString(1, username);
            

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String storedHashedPassword = rs.getString("password");
                    if (BCrypt.checkpw(password, storedHashedPassword)) {
                        return true;  // Password matches
                    }
                    else{
                        return false;
                    }
                }
                return false;  // Username not found or password does not match
            }
        } catch (SQLException e) {
            System.out.println("Error logging in: " + e.getMessage());
            throw e;
        }
  }
}