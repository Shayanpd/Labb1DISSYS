package dao;

// Import required packages for handling SQL connections
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Manages database connection creation and closing for the webshop application.
 */
public class ConnectionManager {
    // Database connection constants
    private static final String URL = "jdbc:mysql://localhost:3306/webshop_db?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root";  // Replace with actual database username
    private static final String PASSWORD = "Shayan123";  // Replace with actual database password

    static {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  // Log error if driver fails to load
        }
    }

    /**
     * Creates and returns a new database connection.
     *
     * @return a new Connection object to the database
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    /**
     * Closes a given database connection if it is open.
     *
     * @param connection the Connection object to close
     */
    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();  // Attempt to close the connection
            } catch (SQLException e) {
                e.printStackTrace();  // Log error if connection fails to close
            }
        }
    }
}
