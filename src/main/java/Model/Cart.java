package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a shopping cart for a user.
 * Contains the cart ID, user ID, and a list of cart items.
 */
public class Cart {
    private int cartId;
    private int userId;
    private List<CartItem> cartItems;

    /**
     * Constructor to initialize Cart with cartId, userId, and a list of CartItems.
     * @param cartId Unique identifier for the cart.
     * @param userId Unique identifier for the user owning the cart.
     * @param cartItems List of cart items.
     */
    public Cart(int cartId, int userId, List<CartItem> cartItems) {
        this.cartId = cartId;
        this.userId = userId;
        this.cartItems = new ArrayList<>(cartItems);
    }

    /**
     * Constructor to initialize Cart with userId and an empty cart.
     * @param userId Unique identifier for the user.
     */
    public Cart(int userId) {
        this(0, userId, new ArrayList<>());
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<CartItem> getCartItems() {
        return new ArrayList<>(cartItems);
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = new ArrayList<>(cartItems);
    }

    /**
     * Adds a CartItem to the cart.
     * @param item The CartItem to add.
     */
    public void addCartItem(CartItem item) {
        cartItems.add(item);
    }

    /**
     * Removes a CartItem from the cart.
     * @param item The CartItem to remove.
     */
    public void removeCartItem(CartItem item) {
        cartItems.remove(item);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", userId=" + userId +
                ", cartItems=" + cartItems +
                '}';
    }
}
