package controller;

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

@WebServlet("/addToCart")
public class AddToCartServlet extends HttpServlet {
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        super.init();
        productService = new ProductService(new ConnectionManager());  // Initialize the ProductService
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        CartDTO cart = (CartDTO) session.getAttribute("cart");

        if (cart == null) {
            // Redirect if no cart exists (which shouldn't happen if handled properly in login)
            response.sendRedirect("products");
            return;
        }

        // Get the product ID and quantity from the form
        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        // Fetch the product from the database using the ProductService
        ProductDTO product = productService.getProductById(productId);

        // Check if product exists, and add it to the cart
        if (product != null) {
            // Use the constructor that takes ProductDTO
            CartItemDTO cartItem = new CartItemDTO(0, product, quantity);

            // Add the item to the cart
            cart.addCartItem(cartItem);

            // Update the cart in the session
            session.setAttribute("cart", cart);
        }

        // Redirect back to the products page or view cart page after adding the product
        response.sendRedirect("viewCart.jsp");
    }
}

