package com.rentals.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rentals.models.Rental;

@Repository
public interface RentalRepository extends CrudRepository<Rental, Long> {
    
}
