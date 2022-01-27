package com.cmcnally.critter.repository;

import com.cmcnally.critter.entity.Schedule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Schedule
 */

@Repository
public interface ScheduleRepository extends CrudRepository<Schedule, Long> {
}
