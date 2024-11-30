package com.rentals;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication

public class RentalPortalApplication {

	public static void main(String[] args) {

		// Chargement des variables d'environnement
        Dotenv dotenv = Dotenv.load();
		dotenv.entries().forEach(entry ->
        System.setProperty(entry.getKey(), entry.getValue())
    	);
		SpringApplication.run(RentalPortalApplication.class, args);
	}
}
