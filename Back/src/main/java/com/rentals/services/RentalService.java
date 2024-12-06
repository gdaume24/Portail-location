package com.rentals.services;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.rentals.dtos.request.PostRentalRequestDto;
import com.rentals.dtos.responses.CreateRentalResponse;
import com.rentals.dtos.responses.RentalDto;
import com.rentals.dtos.responses.RentalsResponse;
import com.rentals.mapper.RentalDtoMapper;
import com.rentals.models.Rental;
import com.rentals.models.User;
import com.rentals.repository.RentalRepository;


@Service
public class RentalService {
    private final RentalRepository rentalRepository;
    private final RentalDtoMapper rentalDtoMapper;
    private final UserService userService;

    @Value("${upload.dir}")
    private String uploadDir;
    
    public RentalService(RentalRepository rentalRepository, RentalDtoMapper rentalDtoMapper, UserService userService) {
        this.rentalRepository = rentalRepository;
        this.rentalDtoMapper = rentalDtoMapper;
        this.userService = userService;
    }

    public CreateRentalResponse createRentalService(PostRentalRequestDto postRentalRequestDto) throws IOException {

        User currentUser = userService.getAuthenticatedUser();        
        
        MultipartFile multipartFile = postRentalRequestDto.getPicture();
        String filename = multipartFile.getOriginalFilename();
        if (filename == null) {
            throw new IllegalArgumentException("The uploaded file must have a name.");
        }
        String fileName = StringUtils.cleanPath(filename);

        // On créé un objet Rental avec le nom du fichier image, l'user sert à indiquer à la clef étrangère de quel user il s'agit
        Rental rental = new Rental();
        rental.setName(postRentalRequestDto.getName());
        rental.setSurface(postRentalRequestDto.getSurface());
        rental.setPrice(postRentalRequestDto.getPrice());
        rental.setDescription(postRentalRequestDto.getDescription());
        rental.setUser(currentUser);
        rental.setPicture(fileName);

        // Le chemin d'enregistrement des images est défini dans le fichier application.properties (C:\Tests)
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Enregistre l'image
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (java.io.IOException e) {
            throw new RuntimeException("Could not save uploaded file: " + fileName, e);
        }

        rentalRepository.save(rental);

        return new CreateRentalResponse().setMessage("Rental created !");
    }

    public CreateRentalResponse updateRental(Long id, PostRentalRequestDto postRentalRequestDto) throws IOException {

        Rental existingRental = rentalRepository.findById(id).orElse(null);

        // Vérifie si l'utilisateur est le propriètaire de l'offre de location 
        Long owner_id = existingRental.getUser().getId();
        Long current_user_id = userService.getAuthenticatedUser().getId();
        if (!owner_id.equals(current_user_id)) {
            throw new RuntimeException("You are not the owner of this rental.");
        }

        // Modifie les valeurs du rental existant
        existingRental.setName(postRentalRequestDto.getName());
        existingRental.setSurface(postRentalRequestDto.getSurface());
        existingRental.setPrice(postRentalRequestDto.getPrice());
        existingRental.setDescription(postRentalRequestDto.getDescription());

        rentalRepository.save(existingRental);

        return new CreateRentalResponse().setMessage("Rental updated !");
    }

    public Rental getRentalById(Long rentalId) {
        return rentalRepository.findById(rentalId)
                .orElseThrow(() -> new RuntimeException("Rental not found with ID: " + rentalId));
    }

    public RentalDto getRentalReponsceDtoById(Long id) {

        Rental rental = getRentalById(id);
        RentalDto rentalDto = rentalDtoMapper.mapToRentalDto(rental);

        return rentalDto;
    }

    public RentalsResponse getRentals() {

        List<Rental> rentals = new ArrayList<>();
        rentalRepository.findAll().forEach(rentals::add);
        List<RentalDto> rentalDtos = rentals.stream()
                                            .map(rentalDtoMapper::mapToRentalDto)  // Applique le mapping sur chaque élément
                                            .collect(Collectors.toList()); 
        
        RentalsResponse response = new RentalsResponse();
        response.setRentals(rentalDtos);

        return response;
    }
    
}
