package ClassRecord;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
public class DataHandler {
    public static ArrayList<String> getAllData(){
        ArrayList<String> userList = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM users";
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Process the results
                    String columnName = rs.getString("username");
                    // Do something with the data
                    //JOptionPane.showMessageDialog(null, "The data are " + columnName);
                    userList.add(columnName);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database connection error: " + e.getMessage());
        }
        return userList;
    }

    public static boolean loginUser(String uname, String pass) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, uname);
            pstmt.setString(2, pass);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // User exists with the provided username and password
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // No user found with the provided username and password
        return false;
    }

    //Create User
    public static boolean registerUser(String uname, String pass) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            // Check if username exists
            String checkQuery = "SELECT * FROM users WHERE username = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setString(1, uname);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                // Insert new user
                String insertQuery = "INSERT INTO users (username, password) VALUES (?, ?)";
                PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
                insertStmt.setString(1, uname);
                insertStmt.setString(2, pass);
                insertStmt.executeUpdate();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
