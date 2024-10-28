package service;

import dao.ConnectionManager;
import dao.ProductDAO;
import Model.Product;
import dto.ProductDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Service layer for managing products
 * Provides business logic for product-related operations.
 */
public class ProductService {
    private final ProductDAO productDAO;

    /**
     * Constructor initializes ProductService with a connection manager.
     * @param connectionManager Manages the database connection.
     */
    public ProductService(ConnectionManager connectionManager) {
        this.productDAO = new ProductDAO(connectionManager);
    }

    /**
     * Retrieves all products as a list of ProductDTO.
     * @return List of ProductDTO representing all products.
     */
    public List<ProductDTO> getAllProducts() {
        try {
            List<ProductDTO> products = productDAO.getAllProducts();
            if (products == null) {
                products = new ArrayList<>();
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Retrieves a product by its ID.
     * @param id Product ID.
     * @return ProductDTO of the product, or null if not found.
     */
    public ProductDTO getProductById(int id) {
        try {
            return productDAO.getProductById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Adds a new product to the database.
     * @param product Product model containing product details.
     */
    public void addProduct(Product product) {
        try {
            productDAO.addProduct(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates an existing product in the database.
     * @param product Product model containing updated product details.
     */
    public void updateProduct(Product product) {
        try {
            productDAO.updateProduct(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a product by its ID.
     * @param id Product ID.
     */
    public void deleteProduct(int id) {
        try {
            productDAO.deleteProduct(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
