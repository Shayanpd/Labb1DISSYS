package controller;

// Import necessary packages and classes for database operations and session management
import Model.User;
import dao.*;
import dto.UserDTO;
import dto.CartDTO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Servlet responsible for handling user login requests and session management.
 * Maps to the /login URL endpoint.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    /**
     * Handles POST requests to authenticate a user and manage session setup based on user role.
     *
     * @param request  the HttpServletRequest object containing login credentials
     * @param response the HttpServletResponse object to send responses to the client
     * @throws IOException if an input or output error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Retrieve username and password from the login form
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            // Establish database connection
            Connection connection = ConnectionManager.getConnection();

            // Instantiate UserDAO with the connection for user authentication
            UserDAO userDAO = new UserDAO(connection);

            // Attempt to authenticate the user with the provided credentials
            User user = userDAO.authenticate(username, password);

            if (user != null) {
                // Create a UserDTO for session storage without exposing sensitive details
                UserDTO userDTO = new UserDTO(user.getUserId(), user.getUsername(), user.getEmail(), user.getRole());

                // Set up the user's session with relevant attributes
                HttpSession session = request.getSession();
                session.setAttribute("user", userDTO);

                // Check user role and set session attributes accordingly
                if ("user".equals(userDTO.getRole())) {
                    // Regular users have a cart, so create and store an empty CartDTO
                    CartDTO cartDTO = new CartDTO(userDTO.getUserId());
                    session.setAttribute("cart", cartDTO); // Store the cart in the session

                    // Redirect to the user information page
                    response.sendRedirect("userInfo.jsp");
                } else {
                    // Admin users do not require a cart, remove if it exists
                    session.removeAttribute("cart");
                    response.sendRedirect("admin.jsp");  // Redirect to the admin dashboard
                }

            } else {
                // Authentication failed, redirect back to login with an error message
                response.sendRedirect("login.jsp?error=1");
            }

            // Close the database connection
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=1");  // Handle exceptions and redirect with error
        }
    }
}
