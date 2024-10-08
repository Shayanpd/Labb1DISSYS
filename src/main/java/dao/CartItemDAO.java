package dao;

import Model.CartItem;
import dto.CartItemDTO;

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

    // Fetch cart item by ID as DTO
    public CartItemDTO getCartItemById(int cartItemId) throws SQLException {
        String query = "SELECT * FROM CartItems WHERE cartItemID = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, cartItemId);

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new CartItemDTO(
                    rs.getInt("cartItemID"),
                    rs.getInt("cartID"),
                    rs.getInt("productID"), // Assume productID will be fetched separately for DTO
                    rs.getInt("quantity")
            );
        }
        return null;
    }

    // Add a new cart item using CartItem model
    public void addCartItem(CartItem cartItem) throws SQLException {
        String query = "INSERT INTO CartItems (cartID, productID, quantity) VALUES (?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, cartItem.getCartId());
        stmt.setInt(2, cartItem.getProduct().getProductId());
        stmt.setInt(3, cartItem.getQuantity());
        stmt.executeUpdate();
    }

    // Update cart item using CartItem model
    public void updateCartItem(CartItem cartItem) throws SQLException {
        String query = "UPDATE CartItems SET quantity = ? WHERE cartItemID = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, cartItem.getQuantity());
        stmt.setInt(2, cartItem.getCartItemId());
        stmt.executeUpdate();
    }

    // Delete a cart item by ID
    public void deleteCartItem(int cartItemId) throws SQLException {
        String query = "DELETE FROM CartItems WHERE cartItemID = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, cartItemId);
        stmt.executeUpdate();
    }

    // Fetch all cart items by cart ID as DTOs
    public List<CartItemDTO> getAllCartItemsByCartId(int cartId) throws SQLException {
        List<CartItemDTO> cartItems = new ArrayList<>();
        String query = "SELECT * FROM CartItems WHERE cartID = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, cartId);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            cartItems.add(new CartItemDTO(
                    rs.getInt("cartItemID"),
                    rs.getInt("cartID"),
                    rs.getInt("productID"),
                    rs.getInt("quantity")
            ));
        }
        return cartItems;
    }
}
