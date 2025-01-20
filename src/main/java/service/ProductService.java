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
            List<Product> products = productDAO.getAllProducts();
            List<ProductDTO> productDTOs = new ArrayList<>();
            for (Product product : products) {
                productDTOs.add(new ProductDTO(
                        product.getProductId(),
                        product.getName(),
                        product.getPrice()
                ));
            }
            return productDTOs;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public ProductDTO getProductById(int id) {
        try {
            Product product = productDAO.getProductById(id);
            if (product != null) {
                return new ProductDTO(product.getProductId(), product.getName(), product.getPrice());
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addProduct(Product product) {
        try {
            productDAO.addProduct(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProduct(Product product) {
        try {
            productDAO.updateProduct(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(int id) {
        try {
            productDAO.deleteProduct(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
