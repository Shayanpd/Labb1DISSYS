package dao;

import Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data Access Object (DAO) for managing User-related database operations.
 */
public class UserDAO {
    private final Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public User getUserById(int id) throws SQLException {
        String query = "SELECT * FROM Users WHERE userID = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return new User(
                    rs.getInt("userID"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("role")
            );
        }
        return null;  // Return null if no user is found
    }

    public void addUser(User user) throws SQLException {
        String query = "INSERT INTO Users (username, password, email) VALUES (?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getPassword());
        stmt.setString(3, user.getEmail());
        stmt.executeUpdate();
    }

    public void updateUser(User user) throws SQLException {
        String query = "UPDATE Users SET username = ?, password = ?, email = ? WHERE userID = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getPassword());
        stmt.setString(3, user.getEmail());
        stmt.setInt(4, user.getUserId());
        stmt.executeUpdate();
    }

    public void deleteUser(int userId) throws SQLException {
        String query = "DELETE FROM Users WHERE userID = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, userId);
        stmt.executeUpdate();
    }

    public User authenticate(String username, String password) throws SQLException {
        String query = "SELECT * FROM Users WHERE username = ? AND password = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, username);
        stmt.setString(2, password);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return new User(
                    rs.getInt("userID"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("role")
            );
        }
        return null;  // Return null if authentication fails
    }
}
