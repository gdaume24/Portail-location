package com.rentals.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthMeReponse {
    private Long id;
    private String name;
    private String email;
    private String created_at;
    private String updated_at;
}
