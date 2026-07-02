public class EmployeeManager {

    private Employee[] employees;

    private int currentSize;

    public EmployeeManager(int capacity) {

        employees = new Employee[capacity];
        currentSize = 0;
    }

    // Add Employee
    public void addEmployee(Employee employee) {

        if(currentSize == employees.length) {
        System.out.println( "Array Full");
        return;
        }

        employees[currentSize++] = employee;
        System.out.println("Employee Added");
    }

    // Search Employee
    public Employee searchEmployee(int id) {

        for(int i=0;i<currentSize;i++) {
            if(employees[i].employeeId == id) {
            return employees[i];
            }
        }

        return null;
    }

    // Traverse
    public void displayEmployees() {
        for(int i=0;i<currentSize;i++) {
        System.out.println(employees[i]);
        }
    }

    // Delete
    public void deleteEmployee(int id) {
        for(int i=0;i<currentSize;i++) {
            if(employees[i].employeeId == id) {
                for(int j=i;j<currentSize-1;j++) {
                    employees[j] = employees[j+1];
                }
                employees[currentSize-1] = null;
                currentSize--;
                System.out.println("Employee Deleted");
                return;
            }
        }
        System.out.println("Employee Not Found");
    }
}