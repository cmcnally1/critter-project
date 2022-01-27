package com.cmcnally.critter.repository;

import com.cmcnally.critter.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Employee
 */

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
