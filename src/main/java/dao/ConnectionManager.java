package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static final String URL = "jdbc:mysql://localhost:3306/webshop_db?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root"; // replace with your username
    private static final String PASSWORD = "root"; // replace with your password "&gP3JmX*1h5mV^Ha#UC%6c"
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load the driver
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // Handle the error appropriately
        }
    }

    // Method to get a new connection
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    // Optionally, you could implement a method to close connections if needed
    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace(); // Handle error
            }
        }
    }
}
