package com.cmcnally.critter.service;

import com.cmcnally.critter.entity.Customer;
import com.cmcnally.critter.entity.Pet;
import com.cmcnally.critter.repository.CustomerRepository;
import com.cmcnally.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class for Pet
 */

@Service
@Transactional
public class PetService {

    // inject Pet Repository
    @Autowired
    private PetRepository petRepository;
    // Inject Customer repository
    @Autowired
    private CustomerRepository customerRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    // Save method to persist a pet to the database
    // Need to also update customer table with pets
    // Returns the persisted pet
    public Pet save(Pet pet) {
        // Store saved pet
        Pet savedPet = petRepository.save(pet);
        // Get the owner of the saved pet
        Customer owner = savedPet.getOwner();
        // Add the new pet to owner
        owner.addNewPet(savedPet);
        // Persist the update the the owner
        customerRepository.save(owner);
        // Return the saved pet
        return savedPet;
    }

    // Find pet that matches a given ID
    public Pet findPetById(Long id) {
        // Search for Pet by id. Store value in optional pet in case pet not found
        Optional<Pet> searchPet = petRepository.findById(id);

        // Either return the pet found by ID or throw an exception
        return searchPet.orElseThrow(PetNotFoundException::new);
    }

    // Find all pets in the database
    public List<Pet> findAllPets() {
        // Pet list to store the list of pets retrieved from database
        List<Pet> petList = new ArrayList<>();

        // Find all pets via repo. Returns iterable object
        Iterable<Pet> retrievedPets = petRepository.findAll();

        // Add retrieved pets to pet list
        for (Pet pet : retrievedPets) {
            petList.add(pet);
        }

        // return list of retrieved pets
        return petList;
    }

    // Find all pets in the database owned by specific owner
    public List<Pet> findPetsByOwner(Long ownerId) {
        // Get pets by owner id using customer query in repository
        List<Pet> pets = petRepository.findAllByOwnerId(ownerId);
        // If pets are empty, throw exception
        if (pets.isEmpty()){
            throw new PetNotFoundException();
        }
        // Return list of pets
        return pets;
    }
}
