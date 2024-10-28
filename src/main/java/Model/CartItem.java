package Model;

/**
 * Represents an item in the shopping cart.
 * Contains details about the product and its quantity in the cart.
 */
public class CartItem {
    private int cartItemId;
    private int cartId;
    private Product product;
    private int quantity;

    /**
     * Constructor to initialize CartItem with all details.
     * @param cartItemId Unique identifier for the cart item.
     * @param cartId Unique identifier for the cart.
     * @param product Product associated with this cart item.
     * @param quantity Quantity of the product in the cart.
     */
    public CartItem(int cartItemId, int cartId, Product product, int quantity) {
        this.cartItemId = cartItemId;
        this.cartId = cartId;
        this.product = product;
        this.quantity = quantity;
    }

    /**
     * Constructor to initialize CartItem with product and quantity only.
     * @param product Product associated with this cart item.
     * @param quantity Quantity of the product in the cart.
     */
    public CartItem(Product product, int quantity) {
        this(0, 0, product, quantity);
    }

    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "cartItemId=" + cartItemId +
                ", cartId=" + cartId +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
