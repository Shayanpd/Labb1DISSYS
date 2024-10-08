package dao;

import Model.Cart;
import dto.CartDTO;
import dto.CartItemDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CartDAO {
    private Connection connection;

    public CartDAO(Connection connection) {
        this.connection = connection;
    }

    // Fetch cart by ID as DTO
    public CartDTO getCartById(int cartId, CartItemDAO cartItemDAO) throws SQLException {
        String query = "SELECT * FROM Cart WHERE cartID = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, cartId);

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            // Create the CartDTO
            CartDTO cartDTO = new CartDTO(rs.getInt("cartID"));

            // Populate CartItems in the CartDTO
            List<CartItemDTO> cartItems = cartItemDAO.getAllCartItemsByCartId(cartId);
            cartDTO.setCartItems(cartItems);

            return cartDTO;
        }
        return null;
    }

    // Add a new cart using Cart model
    public void addCart(Cart cart) throws SQLException {
        String query = "INSERT INTO Cart (userID) VALUES (?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, cart.getUserId());
        stmt.executeUpdate();
    }

    // Delete a cart by ID
    public void deleteCart(int cartId) throws SQLException {
        String query = "DELETE FROM Cart WHERE cartID = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, cartId);
        stmt.executeUpdate();
    }
}
