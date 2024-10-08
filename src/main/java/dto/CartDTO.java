package dto;

import java.util.ArrayList;
import java.util.List;

public class CartDTO {
    private int cartId;
    private List<CartItemDTO> cartItems;

    public CartDTO(int cartId) {
        this.cartId = cartId;
        this.cartItems = new ArrayList<>();
    }

    public int getCartId() {
        return cartId;
    }

    public List<CartItemDTO> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemDTO> cartItems) {
        this.cartItems = cartItems;
    }

    public void addCartItem(CartItemDTO cartItem) {
        this.cartItems.add(cartItem);  // Add the new item to the list
    }
}
