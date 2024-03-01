package com.hamza.Car_Sales_Garage.dto;

import com.hamza.Car_Sales_Garage.entities.FuelType;
import com.hamza.Car_Sales_Garage.entities.TransmissionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {
    private Long id;
    @NotBlank(message = "La marque ne peut pas être vide")
    private String make;
    @NotBlank(message = "Le modèle ne peut pas être vide")
    private String model;
    @NotNull(message = "La date d'enregistrement ne peut pas être vide")
    private LocalDate registrationDate;
    @DecimalMin(value = "0.0", message = "Le prix doit être supérieur ou égal à 0")
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Le type de carburant ne peut pas être vide")
    private FuelType fuelType;
    @Min(value = 0, message = "Le kilométrage doit être supérieur ou égal à 0")
    private int mileage;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Le type de transmission ne peut pas être vide")
    private TransmissionType transmission;
    @Lob
    @NotBlank(message = "Le chemin de l'image ne peut pas être vide")
    private String imagePath;

}
