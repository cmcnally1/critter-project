package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    // Inject pet service
    @Autowired
    private PetService petService;
    // Inject Customer service
    @Autowired
    private CustomerService customerService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        // Create a pet entity to be saved using conversion method
        Pet petToSave = convertDTOToEntity(petDTO);
        // Save the pet via service and convert the returned entity to DTO to be returned by controller
        return convertEntityToDTO(petService.save(petToSave));
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        // Get the pet via the service
        Pet retrievedPet = petService.findPetById(petId);
        // Convert retrieved pet to DTO and return
        return convertEntityToDTO(retrievedPet);
    }

    @GetMapping
    public List<PetDTO> getPets(){
        // Get all pets via the service
        List<Pet> retrievedPets = petService.findAllPets();
        // Create new petDTOs list to store values to be returned
        List<PetDTO> petDTOs = new ArrayList<>();
        // Loop through retrieved pets, convert each to DTO and add to DTO list
        for (int i = 0; i < retrievedPets.size(); i++){
            petDTOs.add(convertEntityToDTO(retrievedPets.get(i)));
        }
        // Return PetDTO list
        return petDTOs;
    }


    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        // Get pets via service
        List<Pet> retrievedPets = petService.findPetsByOwner(ownerId);
        // Create new petDTOs list to store values to be returned
        List<PetDTO> petDTOs = new ArrayList<>();
        // Loop through retrieved pets, convert each to DTO and add to DTO list
        for (int i = 0; i < retrievedPets.size(); i++){
            petDTOs.add(convertEntityToDTO(retrievedPets.get(i)));
        }
        // Return PetDTO list
        return petDTOs;
    }

    // Method to convert a pet entity into Data Transfer Object
    private PetDTO convertEntityToDTO(Pet pet){
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO, "owner");

        // Convert owner to id for DTO
        if (pet.getOwner() != null) {
            petDTO.setOwnerId(pet.getOwner().getId());
        }
        return petDTO;
    }

    // Method to convert a pet DTO into an entity
    private Pet convertDTOToEntity(PetDTO petDTO){
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet, "ownerId");

        // Convert owner id in DTO to owner entity for pet
        Long id = petDTO.getOwnerId();
        if (id != null){
            pet.setOwner(customerService.findCustomerById(id));
        }
        return pet;
    }
}
