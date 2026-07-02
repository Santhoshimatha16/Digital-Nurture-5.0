import java.util.Arrays;
import java.util.Comparator;

public class Main {

    // Linear Search
    public static Product linearSearch(Product[] products,
                                       int targetId) {

        for(Product p : products) {

            if(p.productId == targetId) {
                return p;
            }
        }

        return null;
    }

    // Binary Search
    public static Product binarySearch(Product[] products,
                                       int targetId) {

        int left = 0;
        int right = products.length - 1;

        while(left <= right) {

            int mid = (left + right) / 2;

            if(products[mid].productId == targetId) {
                return products[mid];
            }

            else if(products[mid].productId < targetId) {
                left = mid + 1;
            }

            else {
                right = mid - 1;
            }
        }

        return null;
    }

    public static void main(String[] args) {

        Product[] products = {

            new Product(103,"Laptop","Electronics"),
            new Product(101,"Mouse","Accessories"),
            new Product(104,"Keyboard","Accessories"),
            new Product(102,"Monitor","Electronics")
        };

        // Linear Search
        Product result1 =
                linearSearch(products,102);

        if(result1 != null) {
            System.out.println(
                    "Linear Search Found: "
                            + result1.productName);
        }

        // Sort before Binary Search
        Arrays.sort(products,
                Comparator.comparingInt(
                        p -> p.productId));

        Product result2 =
                binarySearch(products,102);

        if(result2 != null) {
            System.out.println(
                    "Binary Search Found: "
                            + result2.productName);
        }
    }
}