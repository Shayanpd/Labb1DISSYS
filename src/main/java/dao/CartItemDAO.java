package dao;

// Import required packages for CartItem model and data transfer objects
import Model.CartItem;
import dto.CartItemDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) for managing CartItem-related database operations.
 */
public class CartItemDAO {
    private Connection connection;

    /**
     * Constructs a CartItemDAO with a given database connection.
     *
     * @param connection the Connection object for database access
     */
    public CartItemDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Retrieves a CartItemDTO by cart item ID.
     *
     * @param cartItemId the unique ID of the cart item to fetch
     * @return the CartItemDTO if found; otherwise, null
     * @throws SQLException if a database access error occurs
     */
    public CartItemDTO getCartItemById(int cartItemId) throws SQLException {
        String query = "SELECT * FROM CartItems WHERE cartItemID = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, cartItemId);

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new CartItemDTO(
                    rs.getInt("cartItemID"),
                    rs.getInt("cartID"),
                    rs.getInt("productID"),
                    rs.getInt("quantity")
            );
        }
        return null;
    }

    /**
     * Adds a new cart item to the database.
     *
     * @param cartItem the CartItem object containing cart item details
     * @throws SQLException if a database access error occurs
     */
    public void addCartItem(CartItem cartItem) throws SQLException {
        String query = "INSERT INTO CartItems (cartID, productID, quantity) VALUES (?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, cartItem.getCartId());
        stmt.setInt(2, cartItem.getProduct().getProductId());
        stmt.setInt(3, cartItem.getQuantity());
        stmt.executeUpdate();
    }

    /**
     * Updates an existing cart item's quantity in the database.
     *
     * @param cartItem the CartItem object containing updated cart item details
     * @throws SQLException if a database access error occurs
     */
    public void updateCartItem(CartItem cartItem) throws SQLException {
        String query = "UPDATE CartItems SET quantity = ? WHERE cartItemID = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, cartItem.getQuantity());
        stmt.setInt(2, cartItem.getCartItemId());
        stmt.executeUpdate();
    }

    /**
     * Deletes a cart item by its ID.
     *
     * @param cartItemId the unique ID of the cart item to delete
     * @throws SQLException if a database access error occurs
     */
    public void deleteCartItem(int cartItemId) throws SQLException {
        String query = "DELETE FROM CartItems WHERE cartItemID = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, cartItemId);
        stmt.executeUpdate();
    }

    /**
     * Retrieves all CartItemDTOs by a given cart ID.
     *
     * @param cartId the unique ID of the cart to retrieve items from
     * @return a list of CartItemDTOs belonging to the specified cart
     * @throws SQLException if a database access error occurs
     */
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
