package controller;

import Model.Product;
import dao.ConnectionManager;
import dto.ProductDTO;
import service.ProductService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/products")  // Handle all requests to /products
public class ProductController extends HttpServlet {
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        super.init();
        ConnectionManager connectionManager = new ConnectionManager(); // Initialize connection manager
        productService = new ProductService(connectionManager);        // Initialize ProductService
    }

    // Handle GET request to display the list of products
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Fetch the list of products using ProductService
            List<ProductDTO> products = productService.getAllProducts();

            // Set the products as a request attribute
            request.setAttribute("products", products);

            // Forward to products.jsp to display the products
            request.getRequestDispatcher("products.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Error fetching products", e);
        }
    }

    // Handle POST request for adding products (example)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // This method can handle form submissions to add/update products
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        float price = Float.parseFloat(request.getParameter("price"));
        int stock = Integer.parseInt(request.getParameter("stock"));

        // Create a Product object
        Product product = new Product(name, description, price, stock);

        // Call the service to add the product
        productService.addProduct(product);

        // Redirect back to the products list
        response.sendRedirect("products");
    }

    @Override
    public void destroy() {
        super.destroy();
        // Cleanup resources if needed
    }
}
