public class Main {

    public static void main(String[] args) {

        TaskManager manager = new TaskManager();

        manager.addTask(101, "Design UI", "Pending");
        manager.addTask(102, "Backend API", "Completed");
        manager.addTask(103, "Testing", "Pending");

        System.out.println("All Tasks:");
        manager.displayTasks();

        System.out.println();

        manager.searchTask(102);

        System.out.println();

        manager.showCompletedCount();

        manager.deleteTask(103);

        System.out.println("\nAfter Deletion:");
        manager.displayTasks();
    }
} 