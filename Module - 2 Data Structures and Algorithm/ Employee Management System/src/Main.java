public class Main {

    public static void main(String[] args) {

        EmployeeManager manager =new EmployeeManager(10);

        manager.addEmployee( new Employee(101, "Santhoshi", "Developer", 50000));

        manager.addEmployee(new Employee(102,"Rahul","Tester",40000));

        manager.addEmployee(new Employee(103,"Priya","Manager",70000));

        System.out.println("\nAll Employees:");

        manager.displayEmployees();
        Employee found =manager.searchEmployee(102);
        System.out.println("\nSearch Result:");
        System.out.println(found);
        manager.deleteEmployee(103);
        System.out.println("\nAfter Deletion:");
        manager.displayEmployees();
    }
}