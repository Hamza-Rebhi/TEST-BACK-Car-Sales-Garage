package com.hamza.Car_Sales_Garage.service;

import com.hamza.Car_Sales_Garage.dto.CarDTO;
import com.hamza.Car_Sales_Garage.entities.Car;
import com.hamza.Car_Sales_Garage.entities.FuelType;

import java.math.BigDecimal;
import java.util.List;

public interface CarService {
    CarDTO addCar(CarDTO carDTO);
    List<CarDTO> getCarsByFuelTypeAndMaxPrice(FuelType fuelType, BigDecimal maxPrice);
    List<String> getAvailableMakes();
    void updateCarPicture(Long carId, String imagePath);
}
