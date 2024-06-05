/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pupapps.classrecordapp.repository;

import java.sql.Connection;
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
    private final DbConnector dbConnector;

    public Accounts(DbConnector dbConnector) {
        this.dbConnector = dbConnector;
    }
    //Method for Creating User's Table
    public void createUsersTable() throws SQLException {
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
    
    //Method for Inserting User to DB
     public void insertUser(String fname, String lname, String username, String password) throws SQLException {
        String insertQuery = "INSERT INTO users (fname, lname, username, password) VALUES (?, ?, ?, ?)";
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {

            pstmt.setString(1, fname);
            pstmt.setString(2, lname);
            pstmt.setString(3, username);
            pstmt.setString(4, password);
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Account successfully created");
        } catch (SQLException e) {
            System.out.println("Error inserting user: " + e.getMessage());
            throw e;  // Rethrow exception to ensure calling code is aware of the failure
        }
    }
     
    //Method for login user
    public boolean loginUser(String username, String password) throws SQLException{
         String loginQuery = "SELECT COUNT(*) FROM users WHERE username = ? AND password = ?";
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(loginQuery)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (!rs.next() || rs.getInt(1) <= 0) {
                    return false;
                } else {
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error logging in: " + e.getMessage());
            throw e;
        }
  }
}