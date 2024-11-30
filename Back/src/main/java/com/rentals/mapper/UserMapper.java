package com.rentals.mapper;

import org.springframework.stereotype.Component;

import com.rentals.dtos.responses.AuthMeReponse;
import com.rentals.models.User;

@Component
public class UserMapper {

    public AuthMeReponse toAuthMeResponse(User user) {

        AuthMeReponse response = new AuthMeReponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setCreated_at(user.getCreatedAt().toString());
        response.setUpdated_at(user.getUpdatedAt().toString());

        return response;
    }
}
