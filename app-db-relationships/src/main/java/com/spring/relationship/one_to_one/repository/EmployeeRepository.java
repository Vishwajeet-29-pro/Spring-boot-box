package com.spring.relationship.one_to_one.repository;

import com.spring.relationship.one_to_one.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
