package com.rentals.services;

import org.springframework.stereotype.Service;

import com.rentals.dtos.request.PostMessageRequestDto;
import com.rentals.dtos.responses.CreateRentalResponse;
import com.rentals.models.Message;
import com.rentals.models.Rental;
import com.rentals.models.User;
import com.rentals.repository.Messagerepository;

@Service
public class MessageService {

    private final Messagerepository messageRepository;
    private final UserService userService;
    private final RentalService rentalService;

    public MessageService(Messagerepository messageRepository, UserService userService, RentalService rentalService) {
        this.messageRepository = messageRepository;
        this.userService = userService;
        this.rentalService = rentalService;
    }

    public CreateRentalResponse postMessage(PostMessageRequestDto postMessageRequestDto) {   

        User user = userService.findUserById(postMessageRequestDto.getUser_id());
        Rental rental = rentalService.getRentalById(postMessageRequestDto.getRental_id());
        
        Message message = new Message();
        message.setUser(user);
        message.setRental(rental);
        message.setMessage(postMessageRequestDto.getMessage());
        
        messageRepository.save(message);
        CreateRentalResponse response = new CreateRentalResponse().setMessage("Message send with success");

        return response;
    }   
}