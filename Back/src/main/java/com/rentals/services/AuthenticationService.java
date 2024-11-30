package com.rentals.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rentals.dtos.request.LoginRequestDto;
import com.rentals.dtos.request.RegisterRequestDto;
import com.rentals.dtos.responses.AuthReponse;
import com.rentals.models.User;
import com.rentals.repository.UserRepository;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationService(
        UserRepository userRepository,
        AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder,
        JwtService jwtService
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthReponse register(RegisterRequestDto registerRequestDto) {
        User user = new User()
                .setName(registerRequestDto.getName())
                .setEmail(registerRequestDto.getEmail())
                .setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
        User registeredUser = userRepository.save(user);
        AuthReponse authReponse = createAuthReponse(registeredUser);

        return authReponse;
    }

    public AuthReponse authenticate(LoginRequestDto loginRequestDto) {
        
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequestDto.getEmail(),
                    loginRequestDto.getPassword()
                )
        );
        User authenticatedUser = (User) authentication.getPrincipal();
        AuthReponse authReponse = createAuthReponse(authenticatedUser);

        return authReponse;
    }

    private AuthReponse createAuthReponse(User user) {
        String jwtToken = jwtService.generateToken(user);
        return new AuthReponse().setToken(jwtToken);
    }

    public boolean hasUserWithEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}