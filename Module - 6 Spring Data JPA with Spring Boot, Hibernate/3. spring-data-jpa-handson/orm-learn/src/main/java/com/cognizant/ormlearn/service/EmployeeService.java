package com.cognizant.ormlearn.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.repository.EmployeeRepository;

/**
 * Service for Employee operations.
 *
 * Includes methods for:
 *  - Hands-on 2: getAllPermanentEmployees() via HQL fetch join
 *  - Hands-on 4: getAverageSalary(int id) via HQL AVG aggregate
 *  - Hands-on 5: getAllEmployeesNative() via native SQL
 */
@Service
public class EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    // ----------------------------------------------------------------
    // Basic CRUD (from previous hands-on)
    // ----------------------------------------------------------------

    @Transactional
    public Employee get(int id) {
        LOGGER.info("Start get id={}", id);
        return employeeRepository.findById(id).get();
    }

    @Transactional
    public void save(Employee employee) {
        LOGGER.info("Start save");
        employeeRepository.save(employee);
        LOGGER.info("End save");
    }

    // ----------------------------------------------------------------
    // Hands-on 2: HQL - Get all permanent employees with fetch joins
    // ----------------------------------------------------------------

    /**
     * Returns all permanent employees with their department and skill list
     * loaded via a single optimized HQL query using LEFT JOIN FETCH.
     */
    @Transactional
    public List<Employee> getAllPermanentEmployees() {
        LOGGER.info("Start getAllPermanentEmployees");
        List<Employee> employees = employeeRepository.getAllPermanentEmployees();
        LOGGER.info("End getAllPermanentEmployees count={}", employees.size());
        return employees;
    }

    // ----------------------------------------------------------------
    // Hands-on 4: HQL aggregate - Average salary by department
    // ----------------------------------------------------------------

    /**
     * Returns the average salary for employees in the given department.
     *
     * @param id department id
     * @return average salary
     */
    @Transactional
    public double getAverageSalary(int id) {
        LOGGER.info("Start getAverageSalary departmentId={}", id);
        double avg = employeeRepository.getAverageSalary(id);
        LOGGER.info("End getAverageSalary avg={}", avg);
        return avg;
    }

    // ----------------------------------------------------------------
    // Hands-on 5: Native SQL query
    // ----------------------------------------------------------------

    /**
     * Returns all employees using a native SQL query (SELECT * FROM employee).
     */
    @Transactional
    public List<Employee> getAllEmployeesNative() {
        LOGGER.info("Start getAllEmployeesNative");
        List<Employee> employees = employeeRepository.getAllEmployeesNative();
        LOGGER.info("End getAllEmployeesNative count={}", employees.size());
        return employees;
    }
}
