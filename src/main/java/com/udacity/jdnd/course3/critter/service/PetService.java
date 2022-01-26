package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class for Pet
 */

@Service
public class PetService {

    // inject Pet Repository
    @Autowired
    private PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    // Save method to persist a pet to the database
    // Returns the ID of the persisted pet
    public Pet save(Pet pet) {
        return petRepository.save(pet);
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

        // Find all pets via repo. Returns iterable object, so iterate into pet list
        petRepository.findAll().forEach(petList::add);

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
