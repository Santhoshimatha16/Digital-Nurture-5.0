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

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.model.Department;
import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.model.Skill;
import com.cognizant.ormlearn.model.Stock;
import com.cognizant.ormlearn.repository.StockRepository;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.DepartmentService;
import com.cognizant.ormlearn.service.EmployeeService;
import com.cognizant.ormlearn.service.SkillService;

@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

    // Service references
    private static CountryService countryService;
    private static StockRepository stockRepository;   // autowired directly per Hands-on 2 instructions
    private static EmployeeService employeeService;
    private static DepartmentService departmentService;
    private static SkillService skillService;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
        LOGGER.info("Inside main");

        countryService    = context.getBean(CountryService.class);
        stockRepository   = context.getBean(StockRepository.class);
        employeeService   = context.getBean(EmployeeService.class);
        departmentService = context.getBean(DepartmentService.class);
        skillService      = context.getBean(SkillService.class);

        // -------------------------------------------------------
        // Hands-on 1: Query Methods on country table
        // -------------------------------------------------------
        testFindCountriesByPartialName();           // Part 1
        testFindCountriesByPartialNameSorted();     // Part 2 - sorted ascending
        testFindCountriesByAlphabet();              // Part 3 - alphabet index

        // -------------------------------------------------------
        // Hands-on 2: Query Methods on stock table
        // -------------------------------------------------------
        testGetFBStocksSeptember2019();
        testGetGoogleStocksAbove1250();
        testGetTop3HighestVolumeDates();
        testGetNetflixLowest3Stocks();

        // -------------------------------------------------------
        // Hands-on 4: ManyToOne - Employee <-> Department
        // -------------------------------------------------------
        testGetEmployee();
        testAddEmployee();
        testUpdateEmployee();

        // -------------------------------------------------------
        // Hands-on 5: OneToMany - Department -> Employees
        // -------------------------------------------------------
        testGetDepartment();

        // -------------------------------------------------------
        // Hands-on 6: ManyToMany - Employee <-> Skill
        // -------------------------------------------------------
        // testGetEmployee() already logs skills (see implementation below)
        testAddSkillToEmployee();
    }

    // ===========================================================
    // Hands-on 1: Country Query Methods
    // ===========================================================

    /** Part 1: Partial name search (e.g. 'ou') */
    private static void testFindCountriesByPartialName() {
        LOGGER.info("Start - testFindCountriesByPartialName [Part 1] for 'ou'");
        List<Country> countries = countryService.findCountriesByPartialName("ou");
        LOGGER.debug("Countries containing 'ou': {}", countries);
        LOGGER.info("End - testFindCountriesByPartialName");
    }

    /** Part 2: Partial name search sorted ascending */
    private static void testFindCountriesByPartialNameSorted() {
        LOGGER.info("Start - testFindCountriesByPartialNameSorted [Part 2] for 'ou'");
        List<Country> countries = countryService.findCountriesByPartialNameSorted("ou");
        LOGGER.debug("Countries containing 'ou' (sorted ASC): {}", countries);
        LOGGER.info("End - testFindCountriesByPartialNameSorted");
    }

    /** Part 3: Alphabet index search (e.g. 'Z' → Zambia, Zimbabwe) */
    private static void testFindCountriesByAlphabet() {
        LOGGER.info("Start - testFindCountriesByAlphabet [Part 3] for 'Z'");
        List<Country> countries = countryService.findCountriesByAlphabet("Z");
        LOGGER.debug("Countries starting with 'Z': {}", countries);
        LOGGER.info("End - testFindCountriesByAlphabet");
    }

    // ===========================================================
    // Hands-on 2: Stock Query Methods
    // ===========================================================

    /** Scenario 1: FB stocks in September 2019 */
    private static void testGetFBStocksSeptember2019() {
        LOGGER.info("Start - testGetFBStocksSeptember2019");
        LocalDate start = LocalDate.of(2019, 9, 1);
        LocalDate end   = LocalDate.of(2019, 9, 30);
        List<Stock> stocks = stockRepository.findByCodeAndDateBetweenOrderByDateAsc("FB", start, end);
        LOGGER.debug("FB September 2019 stocks: {}", stocks);
        LOGGER.info("End - testGetFBStocksSeptember2019");
    }

    /** Scenario 2: Google stocks where close price > 1250 */
    private static void testGetGoogleStocksAbove1250() {
        LOGGER.info("Start - testGetGoogleStocksAbove1250");
        List<Stock> stocks = stockRepository.findByCodeAndCloseGreaterThan("GOOGL", new BigDecimal("1250"));
        LOGGER.debug("GOOGL stocks close > 1250: {}", stocks);
        LOGGER.info("End - testGetGoogleStocksAbove1250");
    }

    /** Scenario 3: Top 3 dates with highest transaction volume */
    private static void testGetTop3HighestVolumeDates() {
        LOGGER.info("Start - testGetTop3HighestVolumeDates");
        List<Stock> stocks = stockRepository.findTop3ByOrderByVolumeDesc();
        LOGGER.debug("Top 3 highest volume: {}", stocks);
        LOGGER.info("End - testGetTop3HighestVolumeDates");
    }

    /** Scenario 4: 3 dates when Netflix stocks were lowest */
    private static void testGetNetflixLowest3Stocks() {
        LOGGER.info("Start - testGetNetflixLowest3Stocks");
        List<Stock> stocks = stockRepository.findTop3ByCodeOrderByCloseAsc("NFLX");
        LOGGER.debug("Netflix lowest 3 stocks: {}", stocks);
        LOGGER.info("End - testGetNetflixLowest3Stocks");
    }

    // ===========================================================
    // Hands-on 4: ManyToOne - Employee <-> Department
    // ===========================================================

    /** Get employee (id=1) along with their department */
    private static void testGetEmployee() {
        LOGGER.info("Start - testGetEmployee");
        Employee employee = employeeService.get(1);
        LOGGER.debug("Employee: {}", employee);
        LOGGER.debug("Department: {}", employee.getDepartment());
        // Hands-on 6: also log skills
        LOGGER.debug("Skills: {}", employee.getSkillList());
        LOGGER.info("End - testGetEmployee");
    }

    /** Add a new employee with department */
    @SuppressWarnings("deprecation")
    private static void testAddEmployee() {
        LOGGER.info("Start - testAddEmployee");
        Employee employee = new Employee();
        employee.setName("John Doe");
        employee.setSalary(55000.00);
        employee.setPermanent(true);
        employee.setDateOfBirth(new Date(90, 5, 15)); // 15-June-1990

        // Fetch department with id=1 and assign to employee
        Department department = departmentService.get(1);
        employee.setDepartment(department);

        employeeService.save(employee);
        LOGGER.debug("Added Employee: {}", employee);
        LOGGER.info("End - testAddEmployee");
    }

    /** Update employee's department */
    private static void testUpdateEmployee() {
        LOGGER.info("Start - testUpdateEmployee");
        // Get employee id=1
        Employee employee = employeeService.get(1);
        // Get a different department (id=2) and assign to employee
        Department department = departmentService.get(2);
        employee.setDepartment(department);
        employeeService.save(employee);
        LOGGER.debug("Updated Employee: {}", employee);
        LOGGER.info("End - testUpdateEmployee");
    }

    // ===========================================================
    // Hands-on 5: OneToMany - Department -> Employees
    // ===========================================================

    /** Get department along with its employee list (FetchType.EAGER) */
    private static void testGetDepartment() {
        LOGGER.info("Start - testGetDepartment");
        Department department = departmentService.get(1);
        LOGGER.debug("Department: {}", department);
        LOGGER.info("End - testGetDepartment");
    }

    // ===========================================================
    // Hands-on 6: ManyToMany - Employee <-> Skill
    // ===========================================================

    /** Add a skill to an employee */
    private static void testAddSkillToEmployee() {
        LOGGER.info("Start - testAddSkillToEmployee");
        // Get employee id=1 and skill id=3 (adjust if needed based on your DB data)
        Employee employee = employeeService.get(1);
        Skill skill = skillService.get(3);

        // Add the skill to employee's skill set
        Set<Skill> skillList = employee.getSkillList();
        skillList.add(skill);
        employee.setSkillList(skillList);

        employeeService.save(employee);
        LOGGER.debug("Employee after adding skill: {}", employee);
        LOGGER.debug("Skills after update: {}", employee.getSkillList());
        LOGGER.info("End - testAddSkillToEmployee");
    }
}
