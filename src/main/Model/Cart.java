package main.Model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private int cartId;
    private int userId;
    private List<CartItem> cartItems;

    public Cart(int cartId, int userId, List<CartItem> cartItems) {
        this.cartId = cartId;
        this.userId = userId;
        this.cartItems = new ArrayList<>(cartItems);
    }

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

    public void addCartItem(CartItem item) {
        cartItems.add(item);
    }

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
