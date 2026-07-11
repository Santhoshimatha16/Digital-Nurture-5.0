package com.cognizant.ormlearn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cognizant.ormlearn.model.Employee;

/**
 * EmployeeRepository with HQL query methods for Hands-on 2, 4 and 5.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // ----------------------------------------------------------------
    // Hands-on 2: Get all permanent employees using HQL with fetch joins
    //
    // KEY TAKEAWAY:
    //   - 'left join' links the table but does NOT populate the bean
    //   - 'left join fetch' links AND populates the bean
    //   - FetchType.EAGER has been removed from Employee.skillList
    //     and Department.employeeList; fetch is done here instead.
    // ----------------------------------------------------------------

    /**
     * HQL - get permanent employees with department and skills loaded in ONE query.
     * Uses LEFT JOIN FETCH to avoid LazyInitializationException and N+1 queries.
     */
    @Query(value = "SELECT e FROM Employee e "
            + "left join fetch e.department d "
            + "left join fetch e.skillList "
            + "WHERE e.permanent = 1")
    List<Employee> getAllPermanentEmployees();

    // ----------------------------------------------------------------
    // Hands-on 4: Average salary aggregate function using HQL
    //
    // NOTE:
    //   - e.department.id navigates through the association
    //   - :id is a named parameter bound via @Param
    //   - Similar to AVG(), SUM(), MIN(), MAX() are also supported
    // ----------------------------------------------------------------

    /**
     * HQL - compute average salary for a specific department.
     *
     * @param id department id
     * @return average salary as double
     */
    @Query(value = "SELECT AVG(e.salary) FROM Employee e where e.department.id = :id")
    double getAverageSalary(@Param("id") int id);

    // ----------------------------------------------------------------
    // Hands-on 5: Native SQL query (direct SQL, not HQL)
    //
    // NOTE:
    //   - nativeQuery=true tells Spring Data JPA to execute raw SQL
    //   - Avoid native queries when possible for DB portability
    // ----------------------------------------------------------------

    /**
     * Native SQL - get all employees directly via SQL (not HQL).
     */
    @Query(value = "SELECT * FROM employee", nativeQuery = true)
    List<Employee> getAllEmployeesNative();
}
