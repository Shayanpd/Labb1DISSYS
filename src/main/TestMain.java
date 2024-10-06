package main;

import Model.Product;
import dao.ConnectionManager;
import service.ProductService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TestMain {
    public static void main(String[] args) {
        // Set up the connection manager and product DAO
        ConnectionManager connectionManager = new ConnectionManager();
        ProductService productService = new ProductService(connectionManager);

        // Check the database connection
        try (Connection connection = connectionManager.getConnection()) {
            if (connection != null) {
                System.out.println("Database connected successfully!");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Fetch and display all products
        try {
            List<Product> products = productService.getAllProducts(); // Fetch products from the service layer
            System.out.println("All Products: ");
            for (Product product : products) {
                System.out.println(product);
            }
        } catch (Exception e) {
            System.err.println("Error fetching products: " + e.getMessage());
        } finally {
            // Cleanup resources if necessary
            connectionManager.closeConnection(null); // If there's a specific connection to close
        }
    }
}
