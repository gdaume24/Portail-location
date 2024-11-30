package com.rentals.dtos.request;

import lombok.Getter;

@Getter
public class RegisterRequestDto {
    private String email;
    private String password;
    private String name;
}
