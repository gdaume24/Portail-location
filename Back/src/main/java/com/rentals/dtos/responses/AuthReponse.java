package com.rentals.dtos.responses;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
public class AuthReponse {
    private String token;
}