package com.rentals.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentals.dtos.responses.AuthMeReponse;
import com.rentals.services.UserService;

@RequestMapping("")
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("user/{id}")
    public ResponseEntity<AuthMeReponse> getUser(@PathVariable Long id) {

        AuthMeReponse response = userService.findFormattedReponseById(id);

        return ResponseEntity.ok(response);
    }
}
