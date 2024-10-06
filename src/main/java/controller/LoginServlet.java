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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
                // User authenticated successfully, proceed with session handling
                request.getSession().setAttribute("user", user);

                if ("admin".equals(user.getRole())) {
                    response.sendRedirect("admin.jsp");  // Redirect to admin page
                } else {
                    response.sendRedirect("products.jsp");  // Redirect to user page
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