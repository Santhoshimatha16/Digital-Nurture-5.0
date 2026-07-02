public class Main {

    public static void main(String[] args) {

        Inventory inventory = new Inventory();

        inventory.addProduct(new Product1(101,"Laptop",10,50000));

        inventory.addProduct(new Product1(102,"Mouse",50,500));

        inventory.displayProducts();

        inventory.updateProduct(101,20);

        inventory.deleteProduct(102);

        System.out.println("\nAfter Update/Delete:");

        inventory.displayProducts();
    }
}