package controller;


import Model.*;
import dao.*;
import jakarta.servlet.ServletException;
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

            // Authenticate the user
            User user = userDAO.authenticate(username, password);

            if (user != null) {
                // Store the specific user in session (including userID, username, etc.)
                HttpSession session = request.getSession();
                session.setAttribute("user", user);  // Store the entire User object

                // Redirect based on role or other logic
                if ("admin".equals(user.getRole())) {
                    response.sendRedirect("admin.jsp");  // Redirect to admin page
                } else {
                    response.sendRedirect("userInfo.jsp");  // Redirect to temporary user page for regular users
                }
            } else {
                // Authentication failed
                response.sendRedirect("login.jsp?error=1");
            }

            connection.close();  // Close the connection after use

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=1");  // Handle any exceptions
        }
    }
}