package controller;

// Import necessary packages and classes for product handling and database connection
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

/**
 * Controller servlet responsible for handling requests to display and manage products.
 * Maps to the /products URL endpoint.
 */
@WebServlet("/products")
public class ProductController extends HttpServlet {
    private ProductService productService;

    /**
     * Initializes the servlet and sets up the ProductService with a database connection.
     * This method is called once when the servlet is first loaded.
     *
     * @throws ServletException if initialization fails
     */
    @Override
    public void init() throws ServletException {
        super.init();
        ConnectionManager connectionManager = new ConnectionManager();  // Set up the database connection manager
        productService = new ProductService(connectionManager);          // Initialize ProductService with the connection manager
    }

    /**
     * Handles GET requests to fetch and display the list of products.
     *
     * @param request  the HttpServletRequest object containing client request data
     * @param response the HttpServletResponse object to send responses to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an input or output error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve the list of all products from ProductService
            List<ProductDTO> products = productService.getAllProducts();

            // Store the products list in the request attribute for forwarding to JSP
            request.setAttribute("products", products);

            // Forward to products.jsp to display the products
            request.getRequestDispatcher("products.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Error fetching products", e);  // Throw an exception if product fetching fails
        }
    }

    /**
     * Handles POST requests to add a new product (for example, via an admin form).
     *
     * @param request  the HttpServletRequest object containing form data
     * @param response the HttpServletResponse object to send responses to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an input or output error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve product details from form submission
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        float price = Float.parseFloat(request.getParameter("price"));
        int stock = Integer.parseInt(request.getParameter("stock"));

        // Create a Product instance with the provided details
        Product product = new Product(name, description, price, stock);

        // Add the new product to the database via ProductService
        productService.addProduct(product);

        // Redirect back to the product list page
        response.sendRedirect("products");
    }

    /**
     * Cleans up resources upon servlet destruction, if needed.
     */
    @Override
    public void destroy() {
        super.destroy();
        // Implement any required cleanup code here
    }
}
