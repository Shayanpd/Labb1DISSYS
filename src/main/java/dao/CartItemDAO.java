package dao;

import Model.CartItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartItemDAO {
    private Connection connection;

    public CartItemDAO(Connection connection) {
        this.connection = connection;
    }

    public CartItem getCartItemById(int cartItemId) throws SQLException {
        String query = "SELECT * FROM CartItems WHERE cartItemID = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, cartItemId);

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new CartItem(
                    rs.getInt("cartItemID"),
                    rs.getInt("cartID"),
                    null, // Placeholder for product, needs to be fetched separately
                    rs.getInt("quantity")
            );
        }
        return null;
    }

    public void addCartItem(CartItem cartItem) throws SQLException {
        String query = "INSERT INTO CartItems (cartID, productID, quantity) VALUES (?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, cartItem.getCartId());
        stmt.setInt(2, cartItem.getProduct().getProductId()); // Use productId for foreign key
        stmt.setInt(3, cartItem.getQuantity());
        stmt.executeUpdate();
    }

    public void updateCartItem(CartItem cartItem) throws SQLException {
        String query = "UPDATE CartItems SET quantity = ? WHERE cartItemID = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, cartItem.getQuantity());
        stmt.setInt(2, cartItem.getCartItemId());
        stmt.executeUpdate();
    }

    public void deleteCartItem(int cartItemId) throws SQLException {
        String query = "DELETE FROM CartItems WHERE cartItemID = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, cartItemId);
        stmt.executeUpdate();
    }

    public List<CartItem> getAllCartItemsByCartId(int cartId) throws SQLException {
        List<CartItem> cartItems = new ArrayList<>();
        String query = "SELECT * FROM CartItems WHERE cartID = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, cartId);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            cartItems.add(new CartItem(
                    rs.getInt("cartItemID"),
                    rs.getInt("cartID"),
                    null, // Placeholder for product, needs to be fetched separately
                    rs.getInt("quantity")
            ));
        }
        return cartItems;
    }
}
