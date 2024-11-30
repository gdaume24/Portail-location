package com.rentals.dtos.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRentalRequestDto {
    private String name;
    private Integer surface;
    private Integer price;
    private MultipartFile picture;
    private String description;
}
