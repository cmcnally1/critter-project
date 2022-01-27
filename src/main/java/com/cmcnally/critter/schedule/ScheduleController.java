package com.cmcnally.critter.schedule;

import com.cmcnally.critter.entity.Schedule;
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
        BeanUtils.copyProperties(scheduleDTO, schedule);
        return schedule;
    }

    // Method to convert schedule entity to DTO
    private ScheduleDTO convertScheduleEntityToDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);
        return scheduleDTO;
    }
}
