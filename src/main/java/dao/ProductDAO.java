package dao;

import Model.Product;
import dto.ProductDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private final ConnectionManager connectionManager;

    public ProductDAO(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    // Fetch product by ID as DTO
    public ProductDTO getProductById(int id) throws SQLException {
        String query = "SELECT * FROM Products WHERE productID = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new ProductDTO(rs.getInt("productID"),
                        rs.getString("name"),
                        rs.getFloat("price"));
            }
        }
        return null; // Return null if no product is found
    }

    // Add a new product (no need to return a DTO for adding, but using the model Product)
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

    // Update an existing product (also using model Product)
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

    // Delete a product by ID (model operation)
    public void deleteProduct(int id) throws SQLException {
        String query = "DELETE FROM Products WHERE productID = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Fetch all products as DTOs
    public List<ProductDTO> getAllProducts() throws SQLException {
        List<ProductDTO> products = new ArrayList<>();
        String query = "SELECT * FROM Products";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                products.add(new ProductDTO(rs.getInt("productID"),
                        rs.getString("name"),
                        rs.getFloat("price")));
            }
        }
        return products; // Return the list of ProductDTOs
    }
}
