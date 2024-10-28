package Model;

/**
 * Represents a product in the store.
 * Contains details about the product such as ID, name, description, price, and stock.
 */
public class Product {
    private int productId;
    private String name;
    private String description;
    private float price;
    private int stock;

    /**
     * Constructor to initialize Product with full details.
     * @param productId Unique identifier for the product.
     * @param name Name of the product.
     * @param description Description of the product.
     * @param price Price of the product.
     * @param stock Available stock of the product.
     */
    public Product(int productId, String name, String description, float price, int stock) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    /**
     * Constructor to initialize Product without ID.
     * @param name Name of the product.
     * @param description Description of the product.
     * @param price Price of the product.
     * @param stock Available stock of the product.
     */
    public Product(String name, String description, float price, int stock) {
        this(0, name, description, price, stock);
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}
