package dto;

import java.util.ArrayList;
import java.util.List;

public class CartDTO {
    private int cartId;
    private List<CartItemDTO> cartItems;

    // Constructor
    public CartDTO(int cartId) {
        this.cartId = cartId;
        this.cartItems = new ArrayList<>();
    }

    // Getters and setters
    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public List<CartItemDTO> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemDTO> cartItems) {
        this.cartItems = cartItems;
    }

    // Add item to the cart
    public void addCartItem(CartItemDTO cartItem) {
        cartItems.add(cartItem);
    }

    // Remove item from the cart
    public void removeCartItem(CartItemDTO cartItem) {
        cartItems.remove(cartItem);
    }
}
