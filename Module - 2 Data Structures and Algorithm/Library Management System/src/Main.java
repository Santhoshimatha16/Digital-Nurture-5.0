public class Main {

    public static void main(String[] args) {

        TaskLinkedList tasks =new TaskLinkedList();

        tasks.addTask(101, "Design UI","Pending");
        tasks.addTask(102,"Develop Backend","In Progress");
        tasks.addTask(103,"Testing","Pending");
        System.out.println("All Tasks:");
        tasks.displayTasks();
        System.out.println();
        tasks.searchTask(102);
        tasks.deleteTask(103);
        System.out.println("\nAfter Deletion:");
        tasks.displayTasks();
    }
}