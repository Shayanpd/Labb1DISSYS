package controller;

import Model.Product;
import dao.ConnectionManager;
import dao.ProductDAO;
import dto.ProductDTO;
import service.ProductService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class ProductController extends HttpServlet {
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        super.init();
        ConnectionManager connectionManager = new ConnectionManager();  // Set up the database connection manager
        productService = new ProductService(connectionManager);          // Initialize ProductService with the connection manager
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<ProductDTO> products = productService.getAllProducts();
            request.setAttribute("products", products);
            request.getRequestDispatcher("products.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Error fetching products", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        float price = Float.parseFloat(request.getParameter("price"));
        int stock = Integer.parseInt(request.getParameter("stock"));

        Product product = new Product(name, description, price, stock);
        productService.addProduct(product);
        response.sendRedirect("products");
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
