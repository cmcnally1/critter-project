package com.cmcnally.critter.repository;

import com.cmcnally.critter.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Pet
 */

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    // find a list of pets by their owner id via custom SQL query
    @Query("select p from Pet p where p.owner.id = :owner_id")
    List<Pet> findAllByOwnerId(@Param("owner_id") Long ownerId);
}
