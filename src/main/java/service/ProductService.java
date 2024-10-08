package service;

import dao.ConnectionManager;
import dao.ProductDAO;
import Model.Product;
import dto.ProductDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private final ProductDAO productDAO;

    public ProductService(ConnectionManager connectionManager) {
        this.productDAO = new ProductDAO(connectionManager);
    }

    public List<ProductDTO> getAllProducts() {
        try {
            List<ProductDTO> products = productDAO.getAllProducts();
            if (products == null) {
                products = new ArrayList<>();  // Ensure products is not null
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the error appropriately
            return new ArrayList<>(); // Return an empty list in case of error
        }
    }


    // Get product by ID
    public ProductDTO getProductById(int id) {
        try {
            return productDAO.getProductById(id);
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the error appropriately
            return null;
        }
    }

    // Add a new product
    public void addProduct(Product product) {
        try {
            productDAO.addProduct(product);
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the error appropriately
        }
    }

    // Update an existing product
    public void updateProduct(Product product) {
        try {
            productDAO.updateProduct(product);
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the error appropriately
        }
    }

    // Delete a product by ID
    public void deleteProduct(int id) {
        try {
            productDAO.deleteProduct(id);
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the error appropriately
        }
    }
}
