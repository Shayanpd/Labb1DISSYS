<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dto.ProductDTO" %>
<%@ page import="dto.UserDTO" %>
<%@ page import="java.util.List" %>
<%@ include file="header.jsp" %>

<%
    // Check if the user is logged in
    UserDTO user = (UserDTO) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");  // Redirect to login if not logged in
        return;
    }

    // Check if the logged-in user is not an admin
    if ("admin".equals(user.getRole())) {
        out.println("<p>Admins do not have access to the shopping cart.</p>");
        return;
    }

    // Get the list of products from the request (now using ProductDTO)
    List<ProductDTO> products = (List<ProductDTO>) request.getAttribute("products");
%>

<html>
<head>
    <title>Product List</title>
</head>
<body>
    <h1>Products Available</h1>

    <% if (products != null && !products.isEmpty()) { %>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Add to Cart</th>
            </tr>
            <% for (ProductDTO product : products) { %>
                <tr>
                    <td><%= product.getProductId() %></td>
                    <td><%= product.getName() %></td>
                    <td><%= product.getPrice() %></td>
                    <td>
                        <form action="addToCart" method="post">
                            <input type="hidden" name="productId" value="<%= product.getProductId() %>">
                            <label for="quantity">Quantity:</label>
                            <input type="number" name="quantity" value="1" min="1">
                    </td>
                    <td>
                            <input type="submit" value="Add to Cart">
                        </form>
                    </td>
                </tr>
            <% } %>
        </table>
    <% } else { %>
        <p>No products are available at the moment. Please check back later.</p>
    <% } %>

    <p><a href="viewCart.jsp">View Cart</a></p> <!-- Link to view the cart -->
</body>
</html>
