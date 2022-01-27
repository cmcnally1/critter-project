package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Service class for Employee
 */

@Service
@Transactional
public class EmployeeService {

    // Inject Employee Repository
    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Save method to persist an employee to the database
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Method to get employee by their id
    public Employee getEmployeeById(Long employeeId) {
        // Search for employee by id. Store value in optional employee in case none found
        Optional<Employee> searchEmployee = employeeRepository.findById(employeeId);
        //Either return the found employee or throw an exception
        return searchEmployee.orElseThrow(EmployeeNotFoundException::new);
    }

    // Method to set an employee's availability
    public void setEmployeeAvailability(Set<DayOfWeek> availabilityDays, Long employeeId){
        // Find employee to match given id and return in an optional employee
        Optional<Employee> searchEmployee = employeeRepository.findById(employeeId);
        // Store the found employee in variable, or if not found throw exception
        Employee employee = searchEmployee.orElseThrow(EmployeeNotFoundException::new);
        // Set the employees availability
        employee.setDaysAvailable(availabilityDays);
    }

    // Method to return a list of suitable employees based on required date and skills
    public List<Employee> findSuitableEmployees(Set<EmployeeSkill> requiredSkills, LocalDate requiredDate) {

        // Create 2 lists, one for holding all employees and one to hold employees suitable for this appointment
        List<Employee> allEmployees = new ArrayList<>();
        List<Employee> suitableEmployees = new ArrayList<>();

        // Retrieve all employees
        Iterable<Employee> retrievedEmployees = employeeRepository.findAll();

        // Add all retrieved employees to list of employees
        for (Employee employee : retrievedEmployees) {
            allEmployees.add(employee);
        }

        // Loop through all employees to find a suitable employee
        for (int i = 0; i < allEmployees.size(); i++){
            // If the current employee has the required skills
            if (allEmployees.get(i).getSkills().containsAll(requiredSkills)){
                // And if the current employee is available on the required day
                if (allEmployees.get(i).getDaysAvailable().contains(requiredDate.getDayOfWeek())){
                    // Add the current employee to the list of suitable employees
                    suitableEmployees.add(allEmployees.get(i));
                }
            }
            // Otherwise, do nothing and move onto the next employee in the list
        }

        // If no suitable employees are found, return exception
        if (suitableEmployees.isEmpty()){
            throw new EmployeeNotFoundException();
        }

        // Return list of suitable employees
        return suitableEmployees;
    }
}
