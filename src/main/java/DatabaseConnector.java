import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/class-record-db";
    private static final String USER = "postgres";
    private static final String PASS = "pass123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public static void main(String[] args) throws SQLException {
        JOptionPane.showMessageDialog(null, "Connected Successfully!");
    }
}

