<%@ page import="Model.User" %>
<html>
<head>
    <title>User Info</title>
</head>
<body>
    <h1>Testing userInfo.jsp</h1>
    <%
        // Check if the user object is in the session
        User user = (User) session.getAttribute("user");
        if (user == null) {
            out.println("<p>No user in session. Redirecting to login...</p>");
            response.sendRedirect("login.jsp");
            return;
        } else {
            out.println("<p>User found in session!</p>");
        }
    %>
    <h2>Welcome, <%= user.getUsername() %>!</h2>
    <p>Your role is: <%= user.getRole() %></p>
</body>
</html>
