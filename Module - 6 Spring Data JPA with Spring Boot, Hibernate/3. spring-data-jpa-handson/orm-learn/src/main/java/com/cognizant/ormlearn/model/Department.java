package com.cognizant.ormlearn.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Hands-on 2 (previous): Department entity.
 * Table: department (dp_id, dp_name)
 *
 * NOTE (Hands-on 2 of this module):
 * EAGER fetch is intentionally REMOVED from employeeList.
 * The HQL query uses 'left join fetch' to load employees when needed.
 * This avoids the N+1 problem.
 */
@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dp_id")
    private int id;

    @Column(name = "dp_name")
    private String name;

    // LAZY (default) - use HQL fetch join instead of FetchType.EAGER
    @OneToMany(mappedBy = "department")
    private Set<Employee> employeeList;

    public Department() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Set<Employee> getEmployeeList() { return employeeList; }
    public void setEmployeeList(Set<Employee> employeeList) { this.employeeList = employeeList; }

    @Override
    public String toString() {
        return "Department [id=" + id + ", name=" + name + "]";
    }
}
