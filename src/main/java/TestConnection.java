import java.sql.*;
import javax.swing.JOptionPane;

public class TestConnection {

    public static void main(String[] args) {
        try (Connection conn = DatabaseConnector.getConnection()) {
            String sql = "SELECT * FROM users";
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Process the results
                    String columnName = rs.getString("username");
                    // Do something with the data
                    JOptionPane.showMessageDialog(null, "The data are " + columnName);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database connection error: " + e.getMessage());
        }
    }
}
