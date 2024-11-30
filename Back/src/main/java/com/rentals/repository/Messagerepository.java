package com.rentals.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rentals.models.Message;

@Repository
public interface Messagerepository extends CrudRepository<Message, Long> {
    
}
