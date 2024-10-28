package dao;

import Model.Product;
import dto.ProductDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) for managing Product-related database operations.
 */
public class ProductDAO {
    private final ConnectionManager connectionManager;

    /**
     * Constructs a ProductDAO with a specified ConnectionManager for database connections.
     *
     * @param connectionManager the ConnectionManager object for database access
     */
    public ProductDAO(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    /**
     * Retrieves a ProductDTO by product ID.
     *
     * @param id the unique ID of the product to fetch
     * @return the ProductDTO if found; otherwise, null
     * @throws SQLException if a database access error occurs
     */
    public ProductDTO getProductById(int id) throws SQLException {
        String query = "SELECT * FROM Products WHERE productID = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new ProductDTO(
                        rs.getInt("productID"),
                        rs.getString("name"),
                        rs.getFloat("price")
                );
            }
        }
        return null;  // Return null if no product is found
    }

    /**
     * Adds a new product to the database using a Product model.
     *
     * @param product the Product object containing product details
     * @throws SQLException if a database access error occurs
     */
    public void addProduct(Product product) throws SQLException {
        String query = "INSERT INTO Products (name, description, price, stock) VALUES (?, ?, ?, ?)";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setFloat(3, product.getPrice());
            stmt.setInt(4, product.getStock());
            stmt.executeUpdate();
        }
    }

    /**
     * Updates an existing product in the database using a Product model.
     *
     * @param product the Product object containing updated product details
     * @throws SQLException if a database access error occurs
     */
    public void updateProduct(Product product) throws SQLException {
        String query = "UPDATE Products SET name = ?, description = ?, price = ?, stock = ? WHERE productID = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setFloat(3, product.getPrice());
            stmt.setInt(4, product.getStock());
            stmt.setInt(5, product.getProductId());
            stmt.executeUpdate();
        }
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id the unique ID of the product to delete
     * @throws SQLException if a database access error occurs
     */
    public void deleteProduct(int id) throws SQLException {
        String query = "DELETE FROM Products WHERE productID = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    /**
     * Retrieves all products as a list of ProductDTOs.
     *
     * @return a list of ProductDTOs representing all products
     * @throws SQLException if a database access error occurs
     */
    public List<ProductDTO> getAllProducts() throws SQLException {
        List<ProductDTO> products = new ArrayList<>();
        String query = "SELECT * FROM Products";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                products.add(new ProductDTO(
                        rs.getInt("productID"),
                        rs.getString("name"),
                        rs.getFloat("price")
                ));
            }
        }
        return products;
    }
}
