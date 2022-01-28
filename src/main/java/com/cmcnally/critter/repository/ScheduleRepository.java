package com.cmcnally.critter.repository;

import com.cmcnally.critter.entity.Employee;
import com.cmcnally.critter.entity.Pet;
import com.cmcnally.critter.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Schedule
 */

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    // Find a list of schedules by pet
    List<Schedule> findAllByPetsContaining(Pet pet);

    // Find a list of schedules by employee
    List<Schedule> findAllByEmployeesContaining(Employee employee);
}
