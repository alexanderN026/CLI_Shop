package CLI_Shop;

public class ShoppingCart {
    private String productName;
    private double price;
    private int quantity;

    public ShoppingCart(String productName, double price, int quantity) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    /*
    public void setProductName(String productName) {
        this.productName = productName;
    }
    */

    public double getPrice() {
        return price;
    }

    /*
    public void setPrice(double price) {
        this.price = price;
    }
    */

    public int getQuantity() {
        return quantity;
    }

    /*
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    */

    public void reduceQuantity(int quantity) {
        this.quantity -= quantity;
    }

    /*
    public void riseQuantity(int quantity) {
        this.quantity += quantity;
    }
    */
}
