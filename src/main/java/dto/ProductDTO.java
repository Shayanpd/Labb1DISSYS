package dto;

/**
 * Data Transfer Object (DTO) representing a product with basic details.
 */
public class ProductDTO {
    private int productId;  // Unique ID of the product
    private String name;  // Name of the product
    private double price;  // Price of the product

    /**
     * Constructor to initialize ProductDTO with specified details.
     *
     * @param productId the unique ID of the product
     * @param name the name of the product
     * @param price the price of the product
     */
    public ProductDTO(int productId, String name, double price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    /**
     * Gets the unique ID of the product.
     *
     * @return the product ID
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Sets the unique ID of the product.
     *
     * @param productId the product ID to set
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * Gets the name of the product.
     *
     * @return the name of the product
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the product.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the price of the product.
     *
     * @return the price of the product
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the product.
     *
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }
}
