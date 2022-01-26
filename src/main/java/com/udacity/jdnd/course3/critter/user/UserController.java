package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    // Inject customer service
    @Autowired
    private CustomerService customerService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        // Create a customer entity to be persisted from the DTO
        Customer customerToSave = convertCustomerDTOToEntity(customerDTO);
        // Save the customer via the service and convert the returned entity to be returned
        return convertCustomerEntityToDTO(customerService.save(customerToSave));
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        // Get all customers via the service
        List<Customer> retrievedCustomers = customerService.findAllCustomers();
        // Create a new list of customer DTOs to store values to be returned
        List<CustomerDTO> customerDTOs = new ArrayList<>();
        // Loop through retrieved customers, convert each to DTO and add to list
        for (int i = 0; i < retrievedCustomers.size(); i++) {
            customerDTOs.add(convertCustomerEntityToDTO(retrievedCustomers.get(i)));
        }
        // Return CustomerDTO list
        return customerDTOs;
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        // Get the customer via the service
        Customer retrievedCustomer = customerService.findCustomerByPet(petId);
        // Convert retrieved customer to DTO and return
        return convertCustomerEntityToDTO(retrievedCustomer);
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        throw new UnsupportedOperationException();
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        throw new UnsupportedOperationException();
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        throw new UnsupportedOperationException();
    }

    // Method to convert a customer entity into Data Transfer Object
    private static CustomerDTO convertCustomerEntityToDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        return customerDTO;
    }

    // Method to convert a customer DTO to an entity
    private static Customer convertCustomerDTOToEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }

}
