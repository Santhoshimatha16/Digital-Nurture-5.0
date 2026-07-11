package com.cognizant.ormlearn;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cognizant.ormlearn.model.Attempt;
import com.cognizant.ormlearn.model.AttemptOption;
import com.cognizant.ormlearn.model.AttemptQuestion;
import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.model.Department;
import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.model.QuizOption;
import com.cognizant.ormlearn.model.Skill;
import com.cognizant.ormlearn.model.Stock;
import com.cognizant.ormlearn.repository.StockRepository;
import com.cognizant.ormlearn.service.AttemptService;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.DepartmentService;
import com.cognizant.ormlearn.service.EmployeeCriteriaService;
import com.cognizant.ormlearn.service.EmployeeService;
import com.cognizant.ormlearn.service.SkillService;

@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

    // Service / Repository references
    private static CountryService countryService;
    private static StockRepository stockRepository;
    private static EmployeeService employeeService;
    private static DepartmentService departmentService;
    private static SkillService skillService;
    private static AttemptService attemptService;
    private static EmployeeCriteriaService employeeCriteriaService;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
        LOGGER.info("Inside main");

        countryService          = context.getBean(CountryService.class);
        stockRepository         = context.getBean(StockRepository.class);
        employeeService         = context.getBean(EmployeeService.class);
        departmentService       = context.getBean(DepartmentService.class);
        skillService            = context.getBean(SkillService.class);
        attemptService          = context.getBean(AttemptService.class);
        employeeCriteriaService = context.getBean(EmployeeCriteriaService.class);

        // -------------------------------------------------------
        // Hands-on 2: Get all permanent employees using HQL
        //   - Demonstrates left join fetch for one-query load
        //   - FetchType.EAGER removed from Employee & Department
        // -------------------------------------------------------
        testGetAllPermanentEmployees();

        // -------------------------------------------------------
        // Hands-on 3: Fetch quiz attempt details using HQL
        //   - Multi-level join fetch across 6 tables
        //   - Formatted output showing questions and options
        // -------------------------------------------------------
        testGetAttemptDetails();

        // -------------------------------------------------------
        // Hands-on 4: Average salary by department using HQL
        //   - AVG aggregate function with @Param binding
        // -------------------------------------------------------
        testGetAverageSalary();

        // -------------------------------------------------------
        // Hands-on 5: Get all employees using native SQL query
        //   - nativeQuery=true bypasses HQL
        // -------------------------------------------------------
        testGetAllEmployeesNative();

        // -------------------------------------------------------
        // Hands-on 6: Criteria API - dynamic WHERE clause
        //   - Amazon-style filter scenario with optional predicates
        // -------------------------------------------------------
        testCriteriaQuery();
    }

    // ===========================================================
    // Hands-on 2: Get All Permanent Employees (HQL)
    // ===========================================================

    /**
     * Tests getAllPermanentEmployees() which uses:
     *   SELECT e FROM Employee e
     *   left join fetch e.department d
     *   left join fetch e.skillList
     *   WHERE e.permanent = 1
     *
     * A SINGLE query is generated (no N+1 queries).
     * FetchType.EAGER has been removed from Employee.skillList and
     * Department.employeeList — 'fetch' in HQL takes that role here.
     */
    private static void testGetAllPermanentEmployees() {
        LOGGER.info("Start");
        List<Employee> employees = employeeService.getAllPermanentEmployees();
        LOGGER.debug("Permanent Employees:{}", employees);
        employees.forEach(e -> LOGGER.debug("Skills:{}", e.getSkillList()));
        LOGGER.info("End");
    }

    // ===========================================================
    // Hands-on 3: Quiz Attempt Details (HQL multi-join fetch)
    // ===========================================================

    /**
     * Tests getAttempt() for userId=1, attemptId=1.
     *
     * HQL join order (as required):
     *   user -> attempt -> attempt_question -> question -> attempt_option -> options
     *
     * Output format (as required):
     *   <question text>
     *    1) <option text>   <score>  <isAnswer>
     *    2) ...
     */
    private static void testGetAttemptDetails() {
        LOGGER.info("Start");
        Attempt attempt = attemptService.getAttempt(1, 1);
        if (attempt == null) {
            LOGGER.debug("No attempt found for userId=1 attemptId=1. "
                    + "Please run quiz.sql first to populate quiz tables.");
            LOGGER.info("End");
            return;
        }

        LOGGER.debug("Username: {}", attempt.getUser().getName());
        LOGGER.debug("Attempted Date: {}", attempt.getDate());

        // Iterate through each attempted question
        for (AttemptQuestion aq : attempt.getAttemptQuestionList()) {
            System.out.println();
            System.out.println(aq.getQuestion().getText());

            // Iterate through each option for this question
            int optionNumber = 1;
            for (AttemptOption ao : aq.getAttemptOptionList()) {
                QuizOption opt = ao.getQuizOption();
                System.out.printf(" %d) %-15s %.1f\t%s%n",
                        optionNumber++,
                        opt.getText(),
                        opt.getScore(),
                        ao.isAnswer());
            }
        }
        LOGGER.info("End");
    }

    // ===========================================================
    // Hands-on 4: Average Salary by Department (HQL Aggregate)
    // ===========================================================

    /**
     * Tests getAverageSalary(int departmentId).
     *
     * HQL: SELECT AVG(e.salary) FROM Employee e where e.department.id = :id
     *
     * Notes:
     *  - e.department.id navigates through the association in HQL
     *  - :id is a named parameter bound via @Param
     */
    private static void testGetAverageSalary() {
        LOGGER.info("Start");
        // Department 1 (Engineering)
        double avgDept1 = employeeService.getAverageSalary(1);
        LOGGER.debug("Average salary for department 1 (Engineering): {}", avgDept1);

        // Department 2 (Human Resources)
        double avgDept2 = employeeService.getAverageSalary(2);
        LOGGER.debug("Average salary for department 2 (HR): {}", avgDept2);

        // Department 3 (Finance)
        double avgDept3 = employeeService.getAverageSalary(3);
        LOGGER.debug("Average salary for department 3 (Finance): {}", avgDept3);
        LOGGER.info("End");
    }

    // ===========================================================
    // Hands-on 5: Get All Employees using Native Query
    // ===========================================================

    /**
     * Tests getAllEmployeesNative() which executes:
     *   SELECT * FROM employee   (nativeQuery = true)
     *
     * Notes:
     *  - Native queries bypass HQL — direct SQL to DB
     *  - Avoid native queries for better DB portability
     *  - Use only when HQL cannot express the required query
     */
    private static void testGetAllEmployeesNative() {
        LOGGER.info("Start");
        List<Employee> employees = employeeService.getAllEmployeesNative();
        LOGGER.debug("All Employees (native):{}", employees);
        LOGGER.info("End");
    }

    // ===========================================================
    // Hands-on 6: Criteria API - Dynamic Query
    // ===========================================================

    /**
     * Demonstrates the Criteria API for dynamic WHERE clause construction.
     *
     * Scenario: Like Amazon's left-side filter — user can pick any combination
     * of criteria and the WHERE clause is built programmatically.
     *
     * Three searches:
     *  1) Filter by name containing "Alice"
     *  2) Filter by minSalary >= 60000 AND permanent = true
     *  3) No filters — returns all employees
     */
    private static void testCriteriaQuery() {
        LOGGER.info("Start");

        // Scenario 1: Filter by name only (dynamic - other criteria null)
        LOGGER.debug("--- Criteria: name contains 'Alice' ---");
        List<Employee> byName = employeeCriteriaService.searchEmployees("Alice", null, null);
        byName.forEach(e -> LOGGER.debug("Employee: {}", e));

        // Scenario 2: Filter by minSalary AND permanent=true
        LOGGER.debug("--- Criteria: salary >= 60000 AND permanent=true ---");
        List<Employee> bySalaryAndPermanent =
                employeeCriteriaService.searchEmployees(null, 60000.0, true);
        bySalaryAndPermanent.forEach(e -> LOGGER.debug("Employee: {}", e));

        // Scenario 3: No filters - returns all
        LOGGER.debug("--- Criteria: no filters (all employees) ---");
        List<Employee> all = employeeCriteriaService.searchEmployees(null, null, null);
        all.forEach(e -> LOGGER.debug("Employee: {}", e));

        LOGGER.info("End");
    }
}
