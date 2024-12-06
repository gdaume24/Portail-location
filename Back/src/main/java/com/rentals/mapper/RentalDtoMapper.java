package com.rentals.mapper;

import org.springframework.stereotype.Service;

import com.rentals.dtos.responses.RentalDto;
import com.rentals.models.Rental;

@Service
public class RentalDtoMapper {

        public RentalDto mapToRentalDto(Rental rental) {

        RentalDto rentalDto = new RentalDto();
        rentalDto.setId(rental.getId());
        rentalDto.setName(rental.getName());
        rentalDto.setSurface(rental.getSurface());
        rentalDto.setPrice(rental.getPrice());
        rentalDto.setDescription(rental.getDescription());
        rentalDto.setOwner_id(rental.getUser() != null ? rental.getUser().getId() : null);
        rentalDto.setCreated_at(rental.getCreatedAt());
        rentalDto.setUpdated_at(rental.getUpdatedAt());

        String baseUrl = "http://localhost:3001/api";
        String imagePath = "/rentals-photos/" + rental.getPicture();
        rentalDto.setPicture(baseUrl + imagePath);
        
        return rentalDto;
    }
}
