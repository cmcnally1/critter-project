package com.cmcnally.critter.repository;

import com.cmcnally.critter.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Customer
 */

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
