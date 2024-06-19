/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pupapps.classrecordapp.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author lenovo
 */
public class Products {
    private static DbConnector dbConnector;
     public Products(DbConnector dbConnector) {
        Products.dbConnector = dbConnector;
    }
     //Method for Creating
    public void createProductsTable() throws SQLException {
        String sql = """
                     CREATE TABLE IF NOT EXISTS products (
                         id SERIAL PRIMARY KEY,
                         product_name VARCHAR(50) NOT NULL,
                         description VARCHAR(50) NOT NULL,
                         quantity VARCHAR(50) NOT NULL
                     )""";
        try (Connection conn = dbConnector.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
            throw e;  // Rethrow exception to ensure calling code is aware of the failure
        }
    }
    
    //Method for Inserting
     public void insertProduct(String product_name, String description, String quantity) throws SQLException {
        String insertQuery = "INSERT INTO products (product_name, description, quantity) VALUES (?, ?, ?)";
       
        try (Connection conn = dbConnector.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {

            pstmt.setString(1, product_name);
            pstmt.setString(2, description);
            pstmt.setString(3, quantity);
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error inserting user: " + e.getMessage());
            throw e;  // Rethrow exception to ensure calling code is aware of the failure
        }
    }
}
