import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateUser {

    public static void main(String[] args) {

        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement()) {

            // Create the table
//            String sql = "CREATE TABLE users (id SERIAL PRIMARY KEY, username VARCHAR(50) NOT NULL, password VARCHAR(50) NOT NULL)";
//            stmt.executeUpdate(sql);

            // Insert default user
            String insertQuery = "INSERT INTO users (username, password) VALUES ('admin1', 'admin1')";
            stmt.executeUpdate(insertQuery);

            System.out.println("Table created successfully...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
