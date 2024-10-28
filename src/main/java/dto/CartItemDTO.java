package dto;

/**
 * Data Transfer Object (DTO) representing an item in a cart, containing product information and quantity.
 */
public class CartItemDTO {
    private int cartItemId;  // Unique ID of the cart item
    private int cartId;  // ID of the cart containing this item
    private ProductDTO product;  // ProductDTO object representing the product details
    private int productId;  // ID of the product (if ProductDTO is not available)
    private int quantity;  // Quantity of the product in the cart

    /**
     * Constructor to initialize CartItemDTO with full ProductDTO.
     *
     * @param cartItemId the unique ID of the cart item
     * @param product the ProductDTO object containing product details
     * @param quantity the quantity of the product in the cart
     */
    public CartItemDTO(int cartItemId, ProductDTO product, int quantity) {
        this.cartItemId = cartItemId;
        this.product = product;
        this.quantity = quantity;
    }

    /**
     * Constructor to initialize CartItemDTO with cart and product IDs.
     *
     * @param cartItemId the unique ID of the cart item
     * @param cartId the ID of the cart containing this item
     * @param productId the ID of the product
     * @param quantity the quantity of the product in the cart
     */
    public CartItemDTO(int cartItemId, int cartId, int productId, int quantity) {
        this.cartItemId = cartItemId;
        this.cartId = cartId;
        this.productId = productId;
        this.quantity = quantity;
    }

    /**
     * Gets the ProductDTO object associated with the cart item.
     *
     * @return the ProductDTO object
     */
    public ProductDTO getProduct() {
        return product;
    }

    /**
     * Sets the ProductDTO object for this cart item.
     *
     * @param product the ProductDTO to set
     */
    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    /**
     * Gets the product ID.
     *
     * @return the product ID
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Gets the quantity of the product in the cart.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the product in the cart.
     *
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
