package com.rentals.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.rentals.dtos.responses.AuthMeReponse;
import com.rentals.mapper.UserMapper;
import com.rentals.models.User;
import com.rentals.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
    }

    public AuthMeReponse findFormattedReponseById(Long id) {

        User user = findUserById(id);
        AuthMeReponse response = userMapper.toAuthMeResponse(user);

        return response;
    }
    
    public User getAuthenticatedUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User currentUser = (User) authentication.getPrincipal();
            return currentUser;
        }

        throw new RuntimeException("Utilisateur non authentifié");
    }

    public AuthMeReponse getAuthenticatedUserAuthMeReponse() {

        User currentUser = getAuthenticatedUser();
        AuthMeReponse response = userMapper.toAuthMeResponse(currentUser);
        
        return response;
        }
}
