package com.rentals.dtos.responses;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RentalDto {
    private Long id;
    private String name;
    private int surface;
    private int price;
    private String picture;
    private String description;
    private Long owner_id;
    private Date created_at;
    private Date updated_at;
}
