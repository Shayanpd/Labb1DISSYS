package dao;

import Model.User;
import dto.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data Access Object (DAO) for managing User-related database operations.
 */
public class UserDAO {
    private final Connection connection;

    /**
     * Constructs a UserDAO with a given database connection.
     *
     * @param connection the Connection object for database access
     */
    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Retrieves a UserDTO by user ID.
     *
     * @param id the unique ID of the user to fetch
     * @return the UserDTO if found; otherwise, null
     * @throws SQLException if a database access error occurs
     */
    public UserDTO getUserById(int id) throws SQLException {
        String query = "SELECT * FROM Users WHERE userID = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return new UserDTO(
                    rs.getInt("userID"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("role")
            );
        }
        return null;  // Return null if no user is found
    }

    /**
     * Adds a new user to the database using a User model.
     *
     * @param user the User object containing user details
     * @throws SQLException if a database access error occurs
     */
    public void addUser(User user) throws SQLException {
        String query = "INSERT INTO Users (username, password, email) VALUES (?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getPassword());
        stmt.setString(3, user.getEmail());
        stmt.executeUpdate();
    }

    /**
     * Updates an existing user's details in the database.
     *
     * @param user the User object containing updated user details
     * @throws SQLException if a database access error occurs
     */
    public void updateUser(User user) throws SQLException {
        String query = "UPDATE Users SET username = ?, password = ?, email = ? WHERE userID = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getPassword());
        stmt.setString(3, user.getEmail());
        stmt.setInt(4, user.getUserId());
        stmt.executeUpdate();
    }

    /**
     * Deletes a user by ID.
     *
     * @param userId the unique ID of the user to delete
     * @throws SQLException if a database access error occurs
     */
    public void deleteUser(int userId) throws SQLException {
        String query = "DELETE FROM Users WHERE userID = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, userId);
        stmt.executeUpdate();
    }

    /**
     * Authenticates a user based on username and password and returns a UserDTO if valid.
     *
     * @param username the username to authenticate
     * @param password the password to authenticate
     * @return a UserDTO if authentication is successful; otherwise, null
     * @throws SQLException if a database access error occurs
     */
    public UserDTO authenticate(String username, String password) throws SQLException {
        String query = "SELECT userID, username, email, role FROM Users WHERE username = ? AND password = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, username);
        stmt.setString(2, password);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return new UserDTO(
                    rs.getInt("userID"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("role")
            );
        }
        return null;  // Return null if authentication fails
    }
}
