package dao;

import Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    // Fetch user by ID
    public User getUserById(int id) throws SQLException {
        String query = "SELECT * FROM Users WHERE userID = ?"; // Ensure table name matches your schema
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            // Make sure to retrieve the email as well
            return new User(rs.getInt("userID"), rs.getString("username"), rs.getString("password"), rs.getString("email"));
        }
        return null; // Return null if no user is found
    }

    // Add a new user
    public void addUser(User user) throws SQLException {
        String query = "INSERT INTO Users (username, password, email) VALUES (?, ?, ?)"; // Include email in the query
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getPassword());
        stmt.setString(3, user.getEmail()); // Set email value
        stmt.executeUpdate();
    }

    // Update user details
    public void updateUser(User user) throws SQLException {
        String query = "UPDATE Users SET username = ?, password = ?, email = ? WHERE userID = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getPassword());
        stmt.setString(3, user.getEmail());
        stmt.setInt(4, user.getUserId());
        stmt.executeUpdate();
    }

    // Delete user by ID
    public void deleteUser(int userId) throws SQLException {
        String query = "DELETE FROM Users WHERE userID = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, userId);
        stmt.executeUpdate();
    }

    public User authenticate(String username, String password) throws SQLException {
        String query = "SELECT username, password, role FROM Users WHERE username = ? AND password = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, username);
        stmt.setString(2, password);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            // If a user is found, return a new User object with username, password, and role
            String role = rs.getString("role");
            return new User(username, password, role);
        }
        return null;  // Return null if authentication fails
    }
    // Additional methods could include finding users by username, etc.
}
