package com.rentals.controllers;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentals.dtos.request.PostRentalRequestDto;
import com.rentals.dtos.responses.CreateRentalResponse;
import com.rentals.dtos.responses.RentalDto;
import com.rentals.dtos.responses.RentalsResponse;
import com.rentals.services.RentalService;
@RequestMapping("rentals")
@RestController
public class RentalsController {

    private final RentalService rentalService;

    public RentalsController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping("")
    public ResponseEntity<CreateRentalResponse> createRental(@ModelAttribute PostRentalRequestDto postRentalRequestDto) throws IOException {

        CreateRentalResponse rentalResponse = rentalService.createRentalService(postRentalRequestDto);

        return ResponseEntity.ok(rentalResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreateRentalResponse> updateRental(@PathVariable Long id, @ModelAttribute PostRentalRequestDto postRentalRequestDto) throws IOException {

        CreateRentalResponse response = rentalService.updateRental(id, postRentalRequestDto);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentalDto> getRentalById(@PathVariable Long id) {

        RentalDto rental = rentalService.getRentalReponsceDtoById(id);
        
        return ResponseEntity.ok(rental);
    }

    @GetMapping("")
    public ResponseEntity<RentalsResponse> getRentals() {

        RentalsResponse response = rentalService.getRentals();

        return ResponseEntity.ok(response);
    }
}
