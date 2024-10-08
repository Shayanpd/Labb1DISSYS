package controller;

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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            // Get the database connection
            Connection connection = ConnectionManager.getConnection();

            // Create UserDAO with the connection
            UserDAO userDAO = new UserDAO(connection);

            // Authenticate the user (this returns a User model)
            UserDTO user = userDAO.authenticate(username, password);

            if (user != null) {
                // Create a UserDTO to store in session (don't expose full User object)
                UserDTO userDTO = new UserDTO(user.getUserId(), user.getUsername(), user.getEmail(), user.getRole());

                // Store the UserDTO in the session
                HttpSession session = request.getSession();
                session.setAttribute("user", userDTO);  // Store the UserDTO

                // Handle logic for regular users vs admin
                if ("user".equals(userDTO.getRole())) {
                    // Create an empty CartDTO for the user and store it in session
                    CartDTO cartDTO = new CartDTO(userDTO.getUserId());
                    session.setAttribute("cart", cartDTO); // Store the cart in the session

                    // Redirect to the user information page
                    response.sendRedirect("userInfo.jsp");

                } else {
                    // Admins don't have a cart, ensure it's removed if it exists
                    session.removeAttribute("cart");
                    response.sendRedirect("admin.jsp");  // Redirect to admin page
                }

            } else {
                // Authentication failed, redirect back to login with an error flag
                response.sendRedirect("login.jsp?error=1");
            }

            connection.close();  // Close the connection after use

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=1");  // Handle any exceptions
        }
    }
}
