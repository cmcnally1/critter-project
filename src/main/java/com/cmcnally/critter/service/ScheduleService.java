package com.cmcnally.critter.service;

import com.cmcnally.critter.entity.Customer;
import com.cmcnally.critter.entity.Employee;
import com.cmcnally.critter.entity.Pet;
import com.cmcnally.critter.entity.Schedule;
import com.cmcnally.critter.repository.CustomerRepository;
import com.cmcnally.critter.repository.EmployeeRepository;
import com.cmcnally.critter.repository.PetRepository;
import com.cmcnally.critter.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class for Schedule
 */

@Service
@Transactional
public class ScheduleService {

    // Inject schedule repository
    @Autowired
    private ScheduleRepository scheduleRepository;
    // Inject Employee Repository
    @Autowired
    private EmployeeRepository employeeRepository;
    // Inject Customer repository
    @Autowired
    private CustomerRepository customerRepository;
    // Inject Pet repository
    @Autowired
    private PetRepository petRepository;

    public ScheduleService(ScheduleRepository scheduleRepository, EmployeeRepository employeeRepository, CustomerRepository customerRepository, PetRepository petRepository) {
        this.scheduleRepository = scheduleRepository;
        this.employeeRepository = employeeRepository;
        this.customerRepository = customerRepository;
        this.petRepository = petRepository;
    }

    // Method to persist a schedule to the database
    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    // Method to get all schedules
    public List<Schedule> findAllSchedules() {
        // Schedule list to store the list of retrieved schedules
        List<Schedule> scheduleList = new ArrayList<>();
        // Find all schedules
        Iterable<Schedule> retrievedSchedules = scheduleRepository.findAll();
        // Add all retrieved schedules to list
        for (Schedule schedule : retrievedSchedules) {
            scheduleList.add(schedule);
        }
        // Return schedule list
        return scheduleList;
    }

    // Method to get all schedules for a pet
    public List<Schedule> findSchedulesForPet(Long petId) {
        // Search for Pet by id. Store value in optional pet in case pet not found
        Optional<Pet> searchPet = petRepository.findById(petId);
        // If searchPet contains a pet, assign it to pet. Otherwise, throw exception
        Pet pet = searchPet.orElseThrow(PetNotFoundException::new);
        // Return the scheduled appointments for that pet
        return pet.getScheduledAppointments();
    }

    // Method to get all scheduled appointments for an employee
    public List<Schedule> findSchedulesForEmployee(Long employeeId) {
        // Search for employee by id
        Optional<Employee> searchEmployee = employeeRepository.findById(employeeId);
        // If searchEmployee contains an employee, assign to employee. Otherwise, error
        Employee employee = searchEmployee.orElseThrow(EmployeeNotFoundException::new);
        // Return the appointments for that employee
        return employee.getScheduledAppointments();
    }

    // Method to get all scheduled appointments for a customer
    // Gather them via the customer's pets
    public List<Schedule> findSchedulesForCustomer(Long customerId) {
        // Search for customer by id
        Optional<Customer> searchCustomer = customerRepository.findById(customerId);
        // If customer found, store in variable. Otherwise, throw exception
        Customer customer = searchCustomer.orElseThrow(CustomerNotFoundException::new);
        // Get the list of pets owned by that customer
        List<Pet> pets = customer.getPets();
        // Create list of appointments to store customer appointments
        List<Schedule> scheduleList = new ArrayList<>();

        // If customer has pets, gather their appointments into list
        if (pets != null) {
            // For each pet owned by the customer
            for (Pet pet : pets) {
                // For each appointment that pet owns
                for (Schedule appointment : pet.getScheduledAppointments()) {
                    // Add that appointment to the list
                    scheduleList.add(appointment);
                }
            }
        }

        // If no appointments, throw exception
        if (scheduleList.isEmpty()) {
            throw new ScheduleNotFoundException();
        }

        // Return list of appointments
        return scheduleList;
    }
}
