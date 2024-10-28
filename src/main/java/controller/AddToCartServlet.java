package controller;

// Import necessary packages and classes for database connection, data transfer objects (DTOs), and services
import dao.ConnectionManager;
import dto.CartDTO;
import dto.CartItemDTO;
import dto.ProductDTO;
import service.ProductService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet responsible for handling requests to add products to the user's cart.
 * Maps to the /addToCart URL endpoint.
 */
@WebServlet("/addToCart")
public class AddToCartServlet extends HttpServlet {

    private ProductService productService; // Service to manage product data interactions

    /**
     * Initializes the servlet and sets up the ProductService with a database connection.
     * This method is called once when the servlet is first loaded.
     *
     * @throws ServletException if initialization fails
     */
    @Override
    public void init() throws ServletException {
        super.init();
        productService = new ProductService(new ConnectionManager());  // Initialize ProductService with database connection
    }

    /**
     * Handles POST requests to add a product to the user's cart.
     *
     * @param request  the HttpServletRequest object containing client request data
     * @param response the HttpServletResponse object to send responses to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an input or output error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the current user's session
        HttpSession session = request.getSession();

        // Attempt to retrieve the user's cart from the session
        CartDTO cart = (CartDTO) session.getAttribute("cart");

        // If the cart is not found in the session, redirect to the products page
        if (cart == null) {
            response.sendRedirect("products");
            return;
        }

        // Retrieve the product ID and quantity from the request parameters
        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        // Fetch the product details from the database using ProductService
        ProductDTO product = productService.getProductById(productId);

        // If the product exists, create a new cart item and add it to the cart
        if (product != null) {
            // Create a CartItemDTO with the retrieved product details and specified quantity
            CartItemDTO cartItem = new CartItemDTO(0, product, quantity);

            // Add the new item to the user's cart
            cart.addCartItem(cartItem);

            // Update the session attribute to reflect the modified cart
            session.setAttribute("cart", cart);
        }

        // Redirect the user to the cart view page after adding the product
        response.sendRedirect("viewCart.jsp");
    }
}
