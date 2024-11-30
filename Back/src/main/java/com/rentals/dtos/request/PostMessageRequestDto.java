package com.rentals.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostMessageRequestDto {
    private Long rental_id;
    private Long user_id;
    private String message;
}
