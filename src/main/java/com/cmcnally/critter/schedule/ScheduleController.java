package com.cmcnally.critter.schedule;

import com.cmcnally.critter.entity.Employee;
import com.cmcnally.critter.entity.Pet;
import com.cmcnally.critter.entity.Schedule;
import com.cmcnally.critter.service.EmployeeService;
import com.cmcnally.critter.service.PetService;
import com.cmcnally.critter.service.ScheduleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    // Inject the schedule service
    @Autowired
    private ScheduleService scheduleService;
    // Inject Employee service
    @Autowired
    private EmployeeService employeeService;
    // Inject Pet service
    @Autowired
    private PetService petService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        // Create a schedule entity to be persisted from the DTO
        Schedule scheduleToSave = convertScheduleDTOToEntity(scheduleDTO);
        // Save the schedule via the service and convert the returned to DTO
        return convertScheduleEntityToDTO(scheduleService.save(scheduleToSave));
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        // Get all schedules via the service
        List<Schedule> retrievedSchedules = scheduleService.findAllSchedules();
        // Create a new list of DTOs to store values
        List<ScheduleDTO> scheduleDTOs = new ArrayList<>();
        // Loop through retrieved schedules, convert each to DTO and add to list
        for (int i = 0; i < retrievedSchedules.size(); i++) {
            scheduleDTOs.add(convertScheduleEntityToDTO(retrievedSchedules.get(i)));
        }
        // Return list
        return scheduleDTOs;
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        // Get schedules that match id
        List<Schedule> retrievedSchedules = scheduleService.findSchedulesForPet(petId);
        // Create a new list of DTOs to store values
        List<ScheduleDTO> scheduleDTOs = new ArrayList<>();
        // Loop through retrieved schedules, convert each to DTO and add to list
        for (int i = 0; i < retrievedSchedules.size(); i++) {
            scheduleDTOs.add(convertScheduleEntityToDTO(retrievedSchedules.get(i)));
        }
        // Return list
        return scheduleDTOs;
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        // Get schedules that match id
        List<Schedule> retrievedSchedules = scheduleService.findSchedulesForEmployee(employeeId);
        // Create a new list of DTOs to store values
        List<ScheduleDTO> scheduleDTOs = new ArrayList<>();
        // Loop through retrieved schedules, convert each to DTO and add to list
        for (int i = 0; i < retrievedSchedules.size(); i++) {
            scheduleDTOs.add(convertScheduleEntityToDTO(retrievedSchedules.get(i)));
        }
        // Return list
        return scheduleDTOs;
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        // Get schedules that match id
        List<Schedule> retrievedSchedules = scheduleService.findSchedulesForCustomer(customerId);
        // Create a new list of DTOs to store values
        List<ScheduleDTO> scheduleDTOs = new ArrayList<>();
        // Loop through retrieved schedules, convert each to DTO and add to list
        for (int i = 0; i < retrievedSchedules.size(); i++) {
            scheduleDTOs.add(convertScheduleEntityToDTO(retrievedSchedules.get(i)));
        }
        // Return list
        return scheduleDTOs;
    }

    // Method to convert schedule DTO to entity
    private Schedule convertScheduleDTOToEntity(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule, "employeeIds", "petIds");

        // Get all employees from the employee ids in DTO
        List<Long> employeeIds = scheduleDTO.getEmployeeIds();
        if (employeeIds != null) {
            List<Employee> employees = new ArrayList<>();
            for (Long id : employeeIds) {
                employees.add(employeeService.getEmployeeById(id));
            }
            schedule.setEmployees(employees);
        }

        // Get all pets from pet ids in DTO
        List<Long> petIds = scheduleDTO.getPetIds();
        if (petIds != null){
            List<Pet> pets = new ArrayList<>();
            for (Long id : petIds) {
                pets.add(petService.findPetById(id));
            }
            schedule.setPets(pets);
        }

        return schedule;
    }

    // Method to convert schedule entity to DTO
    private ScheduleDTO convertScheduleEntityToDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO, "employees", "pets");

        // Convert the list of employees in the entity to employee ids for DTO
        List<Long> employeeIds = new ArrayList<>();
        List<Employee> employees = schedule.getEmployees();
        if (employees != null) {
            for (Employee employee : employees) {
                employeeIds.add(employee.getId());
            }
            // Add ids to DTO
            scheduleDTO.setEmployeeIds(employeeIds);
        }

        // Convert the list of pets for the entity to pet ids for DTO
        List<Long> petIds = new ArrayList<>();
        List<Pet> pets = schedule.getPets();
        if (pets != null) {
            for (Pet pet : pets){
                petIds.add(pet.getId());
            }
            // Add ids to DTO
            scheduleDTO.setPetIds(petIds);
        }

        return scheduleDTO;
    }
}
