package com.cognizant.ormlearn.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.ormlearn.model.Employee;

/**
 * Hands-on 6: Criteria API Service for dynamic Employee queries.
 *
 * Criteria API solves the problem of dynamic WHERE clauses where the user
 * can pick any combination of filter criteria (like Amazon's left-side filters).
 * Instead of hard-coding HQL strings, predicates are programmatically composed.
 *
 * Reference: https://howtodoinjava.com/hibernate/hibernate-criteria-queries-tutorial/
 */
@Service
public class EmployeeCriteriaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeCriteriaService.class);

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Dynamically searches employees based on the provided (optional) criteria.
     *
     * Any parameter that is null is simply ignored — no predicate is added for it.
     * This mirrors the Amazon-style filter scenario described in Hands-on 6.
     *
     * @param name       (optional) employee name containing this substring
     * @param minSalary  (optional) minimum salary filter
     * @param permanent  (optional) permanent status filter (true/false)
     * @return list of matching employees
     */
    @Transactional
    public List<Employee> searchEmployees(String name, Double minSalary, Boolean permanent) {
        LOGGER.info("Start searchEmployees name={} minSalary={} permanent={}", name, minSalary, permanent);

        // Step 1: Get CriteriaBuilder from EntityManager
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        // Step 2: Create CriteriaQuery for Employee result type
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);

        // Step 3: Define the root entity (FROM clause)
        Root<Employee> employee = cq.from(Employee.class);

        // Step 4: Build predicates dynamically
        List<Predicate> predicates = new ArrayList<>();

        if (name != null && !name.isEmpty()) {
            // WHERE em_name LIKE '%name%'
            predicates.add(cb.like(employee.get("name"), "%" + name + "%"));
        }

        if (minSalary != null) {
            // WHERE em_salary >= minSalary
            predicates.add(cb.greaterThanOrEqualTo(employee.get("salary"), minSalary));
        }

        if (permanent != null) {
            // WHERE em_permanent = permanent
            predicates.add(cb.equal(employee.get("permanent"), permanent));
        }

        // Step 5: Apply all predicates combined with AND
        if (!predicates.isEmpty()) {
            cq.where(cb.and(predicates.toArray(new Predicate[0])));
        }

        // Step 6: Execute and return results
        List<Employee> result = entityManager.createQuery(cq).getResultList();
        LOGGER.info("End searchEmployees found={}", result.size());
        return result;
    }
}
