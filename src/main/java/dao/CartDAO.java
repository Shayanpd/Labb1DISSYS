package dao;

import Model.Cart;
import Model.CartItem;

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

    public Cart getCartById(int cartId) throws SQLException {
        String query = "SELECT * FROM Cart WHERE cartID = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, cartId);

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new Cart(
                    rs.getInt("cartID"),
                    rs.getInt("userID"),
                    null // Initially set cartItems to null, will be populated later
            );
        }
        return null;
    }

    public void addCart(Cart cart) throws SQLException {
        String query = "INSERT INTO Cart (userID) VALUES (?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, cart.getUserId());
        stmt.executeUpdate();
    }

    public void deleteCart(int cartId) throws SQLException {
        String query = "DELETE FROM Cart WHERE cartID = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, cartId);
        stmt.executeUpdate();
    }

    // Additional method to populate cart items for a given cart ID
    public void populateCartItems(Cart cart, CartItemDAO cartItemDAO) throws SQLException {
        // Get all items associated with this cart
        List<CartItem> items = cartItemDAO.getAllCartItemsByCartId(cart.getCartId());
        cart.setCartItems(items);
    }
}
