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

    // Verify login credentials
    public boolean verifyUser(String username, String password) throws SQLException {
        String query = "SELECT * FROM Users WHERE username = ? AND password = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, username);
        stmt.setString(2, password);

        ResultSet rs = stmt.executeQuery();
        return rs.next(); // Returns true if a user with the given username and password exists
    }

    // Additional methods could include finding users by username, etc.
}
