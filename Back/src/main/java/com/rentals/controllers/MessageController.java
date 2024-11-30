package com.rentals.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentals.dtos.request.PostMessageRequestDto;
import com.rentals.dtos.responses.CreateRentalResponse;
import com.rentals.services.MessageService;

@RequestMapping("messages")
@RestController
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("")
    public ResponseEntity<CreateRentalResponse> postMessage(@RequestBody PostMessageRequestDto postMessageRequestDto) {

        CreateRentalResponse response = messageService.postMessage(postMessageRequestDto);
        
        return ResponseEntity.ok(response);
    }
}