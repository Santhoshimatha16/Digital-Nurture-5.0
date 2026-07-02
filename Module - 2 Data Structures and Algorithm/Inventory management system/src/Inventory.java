import java.util.HashMap;

public class Inventory {

    HashMap<Integer, Product1> products = new HashMap<>();

    // Add Product
    public void addProduct(Product1 product) {
        products.put(product.productId, product);
        System.out.println("Product Added");
    }

    // Update Product
    public void updateProduct(int id, int quantity) {

        if(products.containsKey(id)) {
            products.get(id).quantity = quantity;
            System.out.println("Product Updated");
        }
    }

    // Delete Product
    public void deleteProduct(int id) {

        products.remove(id);
        System.out.println("Product Deleted");
    }

    // Display Products
    public void displayProducts() {

        for(Product1 p : products.values()) {
            System.out.println(p);
        }
    }
}