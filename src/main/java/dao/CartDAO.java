package dao;

// Import required packages for data transfer objects and SQL handling
import Model.Cart;
import dto.CartDTO;
import dto.CartItemDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Data Access Object (DAO) for managing Cart-related database operations.
 */
public class CartDAO {
    private Connection connection;

    /**
     * Constructs a CartDAO with a given database connection.
     *
     * @param connection the Connection object for database access
     */
    public CartDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Retrieves a CartDTO by cart ID, including its associated CartItems.
     *
     * @param cartId       the unique ID of the cart to fetch
     * @param cartItemDAO  an instance of CartItemDAO to retrieve items within the cart
     * @return the CartDTO if found; otherwise, null
     * @throws SQLException if a database access error occurs
     */
    public CartDTO getCartById(int cartId, CartItemDAO cartItemDAO) throws SQLException {
        String query = "SELECT * FROM Cart WHERE cartID = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, cartId);

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            // Create CartDTO object using cart ID from the result set
            CartDTO cartDTO = new CartDTO(rs.getInt("cartID"));

            // Retrieve and set CartItems using CartItemDAO
            List<CartItemDTO> cartItems = cartItemDAO.getAllCartItemsByCartId(cartId);
            cartDTO.setCartItems(cartItems);

            return cartDTO;
        }
        return null;  // Return null if the cart ID does not exist
    }

    /**
     * Adds a new cart to the database using a Cart model.
     *
     * @param cart the Cart object containing user ID information
     * @throws SQLException if a database access error occurs
     */
    public void addCart(Cart cart) throws SQLException {
        String query = "INSERT INTO Cart (userID) VALUES (?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, cart.getUserId());
        stmt.executeUpdate();
    }

    /**
     * Deletes a cart by its ID.
     *
     * @param cartId the unique ID of the cart to delete
     * @throws SQLException if a database access error occurs
     */
    public void deleteCart(int cartId) throws SQLException {
        String query = "DELETE FROM Cart WHERE cartID = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, cartId);
        stmt.executeUpdate();
    }
}
