package com.example.labb1diss;

import com.mysql.cj.util.SearchMode;
import main.Model.*;
import java.sql.SQLException;
import java.util.List;

/**
 * The WebShopController is responsible for handling user actions and
 * updating the view and model accordingly.
 */
public class WebshopController {
    /*
    private final WebShopView webShopView; // The view layer
    private final WebShopDbInterface webShopDb; // The model layer (Database interface)

    public WebshopController(WebShopDbInterface webShopDb, WebShopView webShopView) {
        this.webShopDb = webShopDb;
        this.webShopView = webShopView;
    }

     */

    /**
     * Search for products by name or category.
     */
    /*
    public void onSearchProduct(String searchFor, SearchMode mode) {
        if (searchFor != null && searchFor.length() >= 1) {
            Runnable searchTask = () -> {
                try {
                    List<Product> result = null;
                    switch (mode) {
                        case Name:
                            result = webShopDb.searchProductByName(searchFor);
                            break;
                        case Category:
                            result = webShopDb.searchProductByCategory(searchFor);
                            break;
                        default:
                            result = List.of();
                    }
                    // Update UI with the result
                    List<Product> finalResult = result;
                    webShopView.updateProductList(finalResult);
                } catch (Exception e) {
                    // Handle exceptions
                    webShopView.showError("Database error while searching for products.");
                }
            };

            Thread searchThread = new Thread(searchTask);
            searchThread.start();
        } else {
            webShopView.showWarning("Enter a search string!");
        }
    }

     */

    /**
     * Add a product to the cart.
     */
    /*
    public void onAddToCart(int userId, Product product, int quantity) {
        Runnable addTask = () -> {
            try {
                webShopDb.addProductToCart(userId, product, quantity);
                webShopView.showSuccess("Product added to the cart.");
            } catch (SQLException e) {
                handleException(e, "Error adding product to the cart.");
            }
        };

        Thread addThread = new Thread(addTask);
        addThread.start();
    }


     */
    /**
     * Remove a product from the cart.
     */
    /*
    public void onRemoveFromCart(int userId, Product product) {
        Runnable removeTask = () -> {
            try {
                webShopDb.removeProductFromCart(userId, product);
                webShopView.showSuccess("Product removed from the cart.");
            } catch (SQLException e) {
                handleException(e, "Error removing product from the cart.");
            }
        };

        Thread removeThread = new Thread(removeTask);
        removeThread.start();
    }

     */

    /**
     * Checkout the cart.
     */
    /*
    public void onCheckout(int userId) {
        Runnable checkoutTask = () -> {
            try {
                boolean success = webShopDb.checkoutCart(userId);
                if (success) {
                    webShopView.showSuccess("Checkout successful.");
                } else {
                    webShopView.showWarning("Checkout failed.");
                }
            } catch (SQLException e) {
                handleException(e, "Error during checkout.");
            }
        };

        Thread checkoutThread = new Thread(checkoutTask);
        checkoutThread.start();
    }

     */

    /**
     * Connect to the database.
     */
    /*
    public boolean connectToDatabase() throws SQLException {
        return webShopDb.connect("jdbc:mysql://localhost:3306/webshop_db", "user", "password");
    }


     */
    /**
     * Disconnect from the database.
     */
    /*
    public void disconnectFromDatabase() throws SQLException {
        webShopDb.disconnect();
    }

    private void handleException(Exception e, String message) {
        webShopView.showError(message + " - " + e.getMessage());
    }

     */

}
