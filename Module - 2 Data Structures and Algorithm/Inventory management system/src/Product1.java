public class Product1 {

    int productId;
    String productName;
    int quantity;
    double price;

    public Product1(int productId, String productName, int quantity, double price) {

        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String toString() {
        return "ID: " + productId +", Name: " + productName +", Quantity: " + quantity +", Price: " + price;
    }
}