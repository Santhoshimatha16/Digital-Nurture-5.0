public class TaskManager {

    private Task head;
    private Task tail;

    private int completedTasks = 0;

    // Add Task
    public void addTask(int id,
                        String name,
                        String status) {

        Task newTask =
                new Task(id,name,status);

        if(head == null) {

            head = tail = newTask;
        }
        else {

            tail.next = newTask;
            tail = newTask;
        }

        if(status.equalsIgnoreCase("Completed")) {
            completedTasks++;
        }
    }

    // Search
    public void searchTask(int id) {

        Task current = head;

        while(current != null) {

            if(current.taskId == id) {

                System.out.println(
                        "Found : "
                        + current.taskName);

                return;
            }

            current = current.next;
        }

        System.out.println(
                "Task Not Found");
    }

    // Traverse
    public void displayTasks() {

        Task current = head;

        while(current != null) {

            System.out.println(
                    current.taskId +
                    " | " +
                    current.taskName +
                    " | " +
                    current.status);

            current = current.next;
        }
    }

    // Delete
    public void deleteTask(int id) {

        if(head == null) return;

        if(head.taskId == id) {

            if(head.status.equalsIgnoreCase("Completed")) {
                completedTasks--;
            }

            head = head.next;
            return;
        }

        Task current = head;

        while(current.next != null) {

            if(current.next.taskId == id) {

                if(current.next.status.equalsIgnoreCase("Completed")) {
                    completedTasks--;
                }

                current.next =
                        current.next.next;

                return;
            }

            current = current.next;
        }
    }

    public void showCompletedCount() {

        System.out.println(
                "Completed Tasks : "
                + completedTasks);
    }
}