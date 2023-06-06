import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class GetData {
    public static ArrayList<String> getAllData(){
        ArrayList<String> userList = new ArrayList<>();
        try (Connection conn = DatabaseConnector.getConnection()) {
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
}
