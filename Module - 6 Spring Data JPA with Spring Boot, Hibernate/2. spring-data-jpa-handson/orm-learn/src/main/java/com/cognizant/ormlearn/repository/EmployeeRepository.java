package com.cognizant.ormlearn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cognizant.ormlearn.model.Employee;

/**
 * Hands-on 3: EmployeeRepository
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
