package controller;

import dao.ConnectionManager;
import service.ProductService;
import Model.Product;


import jakarta.servlet.ServletException; // Changed import
import jakarta.servlet.annotation.WebServlet; // Changed import
import jakarta.servlet.http.HttpServlet; // Changed import
import jakarta.servlet.http.HttpServletRequest; // Changed import
import jakarta.servlet.http.HttpServletResponse; // Changed import

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("")
public class ProductController extends HttpServlet {
    private ProductService productService;
    private ConnectionManager connectionManager;

    @Override
    public void init() throws ServletException {
        super.init();
        connectionManager = new ConnectionManager(); // Initialize connection manager
        productService = new ProductService(connectionManager); // Initialize ProductService
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Fetch products from the database or service
            List<Product> products = productService.getAllProducts();
            if (products == null) {
                products = new ArrayList<>(); // Ensure it's not null
            }
            request.setAttribute("products", products); // Pass the products to the JSP
            request.getRequestDispatcher("index.jsp").forward(request, response); // Forward to JSP
        } catch (Exception e) {
            throw new ServletException("Error fetching products", e);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        // Cleanup resources if needed
    }
}
