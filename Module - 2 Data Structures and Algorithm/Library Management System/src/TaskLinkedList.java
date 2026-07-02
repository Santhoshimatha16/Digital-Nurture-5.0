public class TaskLinkedList {

    private Task head;
    private Task tail;

    // Add Task
    public void addTask(int id,String name,String status) {

        Task newTask =new Task(id,name,status);

        if(head == null) {
            head = tail = newTask;
            return;
        }

        tail.next = newTask;
        tail = newTask;
    }

    // Search Task
    public void searchTask(int id) {

        Task current = head;

        while(current != null) {

            if(current.taskId == id) {
            System.out.println("Task Found -> "+ current.taskName);
            return;
            }

            current = current.next;
        }

        System.out.println("Task Not Found");
    }

    // Traverse
    public void displayTasks() {

        Task current = head;

        while(current != null) {

            System.out.println(
                current.taskId + " | "
                + current.taskName + " | "
                + current.status);

            current = current.next;
        }
    }

    // Delete
    public void deleteTask(int id) {

        if(head == null) {
            return;
        }

        if(head.taskId == id) {

            head = head.next;

            if(head == null) {
                tail = null;
            }

            return;
        }

        Task current = head;

        while(current.next != null) {

            if(current.next.taskId == id) {

                if(current.next == tail) {
                    tail = current;
                }

            current.next =current.next.next;
            return;
            }
            current = current.next;
        }
    }
}