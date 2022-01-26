package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class for Customer
 */

@Service
public class CustomerService {

    // Inject Customer repository
    @Autowired
    private CustomerRepository customerRepository;
    // Inject Pet repository for findCustomerByPet method
    @Autowired
    private PetRepository petRepository;

    public CustomerService(CustomerRepository customerRepository, PetRepository petRepository) {
        this.customerRepository = customerRepository;
        this.petRepository = petRepository;
    }

    // Save method to persist a customer to the database
    // Returns the persisted customer
    public Customer save (Customer customer) {
        return customerRepository.save(customer);
    }

    // Find all customers in the database
    public List<Customer> findAllCustomers() {
        // Customer list to store the list of retrieved customers
        List<Customer> customerList = new ArrayList<>();
        // Find all customers. Returns iterable, so add each to customer list
        customerRepository.findAll().forEach(customerList::add);
        // Return customer list
        return customerList;
    }

    public Customer findCustomerByPet(Long petId) {
        // Search for Pet by id. Store value in optional pet in case pet not found
        Optional<Pet> searchPet = petRepository.findById(petId);
        // If searchPet contains a pet, assign it to pet. Otherwise, throw exception
        Pet pet = searchPet.orElseThrow(PetNotFoundException::new);
        // Return the owner of that pet
        return pet.getOwner();
    }
}
