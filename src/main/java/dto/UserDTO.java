package dto;

/**
 * Data Transfer Object (DTO) representing a user with basic profile details.
 */
public class UserDTO {
    private int userId;  // Unique ID of the user
    private String username;  // Username of the user
    private String email;  // Email of the user
    private String role;  // Role of the user (e.g., "admin", "customer")

    /**
     * Constructor to initialize UserDTO with specified details.
     *
     * @param userId the unique ID of the user
     * @param username the username of the user
     * @param email the email of the user
     * @param role the role of the user
     */
    public UserDTO(int userId, String username, String email, String role) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    /**
     * Gets the unique ID of the user.
     *
     * @return the user ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the unique ID of the user.
     *
     * @param userId the user ID to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the username of the user.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     *
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the email of the user.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     *
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the role of the user.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role of the user.
     *
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }
}
