package Model;

/**
 * Model for User
 * Represents a user in the system with full details.
 */
public class User {
    private int userId;
    private String username;
    private String password;
    private String email;
    private String role;

    /**
     * Constructor with full user details.
     * @param userId Unique identifier for the user.
     * @param username Username of the user.
     * @param password Password of the user.
     * @param email Email address of the user.
     * @param role Role of the user.
     */
    public User(int userId, String username, String password, String email, String role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
