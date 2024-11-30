package com.rentals.dtos.responses;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RentalsResponse {
    private List<RentalDto> rentals;
}



