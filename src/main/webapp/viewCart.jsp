<%@ include file="header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dto.CartDTO" %>
<%@ page import="dto.CartItemDTO" %>
<%@ page import="java.util.List" %>

<html>
<head>
    <title>View Cart</title>
</head>
<body>
    <h1>Your Shopping Cart</h1>

    <%
        // Fetch the cart from the session
        CartDTO cart = (CartDTO) session.getAttribute("cart");
        if (cart == null) {
            out.println("<p>Your cart is empty.</p>");
        } else {
            List<CartItemDTO> cartItems = cart.getCartItems();

            if (cartItems == null || cartItems.isEmpty()) {
                out.println("<p>Your cart is empty.</p>");
            } else {
    %>
                <table border="1">
                    <tr>
                        <th>Product ID</th>
                        <th>Name</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Total</th>
                    </tr>
                    <% for (CartItemDTO item : cartItems) { %>
                        <tr>
                            <td><%= item.getProduct().getProductId() %></td>
                            <td><%= item.getProduct().getName() %></td>
                            <td><%= item.getQuantity() %></td>
                            <td><%= item.getProduct().getPrice() %></td>
                            <td><%= item.getQuantity() * item.getProduct().getPrice() %></td>
                        </tr>
                    <% } %>
                </table>
                <p>Total Price: <strong>
                    <%= cartItems.stream().mapToDouble(item -> item.getQuantity() * item.getProduct().getPrice()).sum() %>
                </strong></p>
    <%
            }
        }
    %>

    <p><a href="products">Continue Shopping</a></p>
</body>
</html>
