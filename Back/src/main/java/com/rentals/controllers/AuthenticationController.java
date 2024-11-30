package com.rentals.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentals.dtos.request.LoginRequestDto;
import com.rentals.dtos.request.RegisterRequestDto;
import com.rentals.dtos.responses.AuthMeReponse;
import com.rentals.dtos.responses.AuthReponse;
import com.rentals.services.AuthenticationService;
import com.rentals.services.UserService;

@RequestMapping("auth")
@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    public AuthenticationController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @PostMapping("register")
    public ResponseEntity<AuthReponse> register(@RequestBody RegisterRequestDto registerRequestDto) {

        if (authenticationService.hasUserWithEmail(registerRequestDto.getEmail())) {
            throw new IllegalArgumentException("Un utilisateur avec cet email existe déjà.");
        }

        AuthReponse authReponse = authenticationService.register(registerRequestDto);

        return ResponseEntity.ok(authReponse);
    }

    @PostMapping("login")
    public ResponseEntity<AuthReponse> authenticate(@RequestBody LoginRequestDto loginRequestDto) {

        AuthReponse authReponse = authenticationService.authenticate(loginRequestDto);

        return ResponseEntity.ok(authReponse);
    }

    @GetMapping("me")
    public ResponseEntity<AuthMeReponse> authenticatedUser() {

        AuthMeReponse response = userService.getAuthenticatedUserAuthMeReponse();
        
        return ResponseEntity.ok(response);
    }
}