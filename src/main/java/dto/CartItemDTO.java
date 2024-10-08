package dto;

public class CartItemDTO {
    private int cartItemId;
    private int cartId;
    private ProductDTO product;
    private int productId;
    private int quantity;

    // Constructor
    public CartItemDTO(int cartItemId, ProductDTO product, int quantity) {
        this.cartItemId = cartItemId;
        this.product = product;
        this.quantity = quantity;
    }
    // Constructor with productID (for initial creation where only productID is known)
    public CartItemDTO(int cartItemId, int cartId, int productId, int quantity) {
        this.cartItemId = cartItemId;
        this.cartId = cartId;
        this.productId = productId;  // Store productId
        this.quantity = quantity;
    }



    // Getters and setters
    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
