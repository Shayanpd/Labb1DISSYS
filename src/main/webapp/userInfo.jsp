<%@ page import="dto.UserDTO" %>
<html>
<head>
    <title>User Info</title>
</head>
<body>
    <h1>Testing userInfo.jsp</h1>
    <%
        // Check if the user object (UserDTO) is in the session
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null) {
            out.println("<p>No user in session. Redirecting to login...</p>");
            response.sendRedirect("login.jsp");
            return;
        } else {
            out.println("<p>User found in session!</p>");
        }
    %>
    <h2>Welcome, <%= user.getUsername() %>!</h2>

    <!-- Button to redirect to products.jsp -->
    <form action="products.jsp" method="get">
        <input type="submit" value="Go to Products">
    </form>

</body>
</html>
