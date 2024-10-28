package dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Data Transfer Object (DTO) representing a shopping cart, containing multiple CartItemDTO objects.
 */
public class CartDTO {
    private int cartId;  // Unique ID of the cart
    private List<CartItemDTO> cartItems;  // List of items in the cart

    /**
     * Constructor to initialize CartDTO with a specified cart ID.
     *
     * @param cartId the unique ID of the cart
     */
    public CartDTO(int cartId) {
        this.cartId = cartId;
        this.cartItems = new ArrayList<>();
    }

    /**
     * Gets the unique ID of the cart.
     *
     * @return the cart ID
     */
    public int getCartId() {
        return cartId;
    }

    /**
     * Gets the list of CartItemDTO objects in the cart.
     *
     * @return the list of cart items
     */
    public List<CartItemDTO> getCartItems() {
        return cartItems;
    }

    /**
     * Sets the list of CartItemDTO objects in the cart.
     *
     * @param cartItems a list of CartItemDTO objects
     */
    public void setCartItems(List<CartItemDTO> cartItems) {
        this.cartItems = cartItems;
    }

    /**
     * Adds a CartItemDTO to the cart.
     *
     * @param cartItem the item to add to the cart
     */
    public void addCartItem(CartItemDTO cartItem) {
        this.cartItems.add(cartItem);
    }
}
