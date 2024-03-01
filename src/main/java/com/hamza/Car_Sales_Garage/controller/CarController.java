package com.hamza.Car_Sales_Garage.controller;

import com.hamza.Car_Sales_Garage.dto.CarDTO;
import com.hamza.Car_Sales_Garage.entities.FuelType;
import com.hamza.Car_Sales_Garage.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/car")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @PostMapping("/add-car")
    @io.swagger.v3.oas.annotations.Operation(summary = "Ajouter une voiture", description = "Permet d'ajouter une voiture au catalogue.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Voiture ajoutée avec succès")

    public ResponseEntity<CarDTO> addCar(@RequestBody CarDTO carDTO) {
        CarDTO addedCar = carService.addCar(carDTO);
        return new ResponseEntity<>(addedCar, HttpStatus.CREATED);
    }

    @GetMapping("/fuel/{fuelType}/price/{maxPrice}")
    @io.swagger.v3.oas.annotations.Operation(summary = "Obtenir les voitures par type de carburant et prix maximum", description = "Permet d'obtenir toutes les voitures en fonction du type de carburant et du prix maximum.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Liste des voitures récupérée avec succès")
    public ResponseEntity<List<CarDTO>> getCarsByFuelTypeAndMaxPrice(
            @PathVariable FuelType fuelType,
            @PathVariable BigDecimal maxPrice) {
        List<CarDTO> cars = carService.getCarsByFuelTypeAndMaxPrice(fuelType, maxPrice);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/makes")
    @io.swagger.v3.oas.annotations.Operation(summary = "Obtenir les marques disponibles", description = "Permet d'obtenir toutes les marques de voitures disponibles dans le catalogue.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Liste des marques récupérée avec succès")
    public ResponseEntity<List<String>> getAvailableMakes() {
        List<String> makes = carService.getAvailableMakes();
        return ResponseEntity.ok(makes);
    }

    @PutMapping("/{carId}/picture")
    @io.swagger.v3.oas.annotations.Operation(summary = "Mettre à jour l'image d'une voiture", description = "Permet de mettre à jour l'image d'une voiture dans le catalogue.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Image de la voiture mise à jour avec succès")
    public ResponseEntity<Void> updateCarPicture(
            @PathVariable Long carId,
            @RequestParam String imagePath) {
        carService.updateCarPicture(carId, imagePath);
        return ResponseEntity.ok().build();
    }

}
