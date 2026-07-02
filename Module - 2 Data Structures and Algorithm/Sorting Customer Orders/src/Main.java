public class Main {

    public static void printOrders(Order[] orders) {

        for(Order o : orders) {
            System.out.println(o);
        }

        System.out.println();
    }

    public static void main(String[] args) {

        Order[] orders = {

                new Order(101,"Santhoshi",2500),
                new Order(102,"Rahul",1200),
                new Order(103,"Priya",5000),
                new Order(104,"Arun",3200)
        };

        System.out.println("Before Sorting:");
        printOrders(orders);
        BubbleSort.sort(orders);
        System.out.println("After Bubble Sort:");
        printOrders(orders);
        QuickSort.quickSort(
                orders,
                0,
                orders.length-1);

        System.out.println(
                "After Quick Sort:");

        printOrders(orders);
    }
}