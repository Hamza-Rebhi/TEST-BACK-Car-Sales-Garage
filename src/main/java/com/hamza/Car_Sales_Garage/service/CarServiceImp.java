package com.hamza.Car_Sales_Garage.service;

import com.hamza.Car_Sales_Garage.dto.CarDTO;
import com.hamza.Car_Sales_Garage.entities.Car;
import com.hamza.Car_Sales_Garage.entities.FuelType;
import com.hamza.Car_Sales_Garage.mappers.CarMapper;
import com.hamza.Car_Sales_Garage.repositories.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarServiceImp  implements  CarService{
    private final CarRepository carRepository;
    //add a car to the garage catalog
    @Override
    public CarDTO addCar(CarDTO carDTO) {
        // Vérifie si la voiture a été enregistrée après 2015
        if (carDTO.getRegistrationDate().isBefore(LocalDate.of(2016, 1, 1))) {
            throw new IllegalArgumentException("La voiture doit être enregistrée après 2015.");
        }
        return CarMapper.INSTANCE.mapCarToCarDTO(carRepository.save(CarMapper.INSTANCE.mapCarDTOToCar(carDTO)));

    }
   // get all the cars by Fuel type and Max Price
    @Override
    public List<CarDTO> getCarsByFuelTypeAndMaxPrice(FuelType fuelType, BigDecimal maxPrice) {
        List<Car> cars= carRepository.findByFuelTypeAndPriceLessThan(fuelType, maxPrice);
        return cars.stream()
                   .map(car->CarMapper.INSTANCE.mapCarToCarDTO(car))
                   .collect(Collectors.toList());

    }
    // get all the make available in the catalog
    @Override
    public List<String> getAvailableMakes() {
        return carRepository.findDistinctMake();

    }
//update a car picture
    @Override
    public void updateCarPicture(Long carId, String imagePath) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new IllegalArgumentException("Voiture non trouvée avec l'ID : " + carId));
        car.setImagePath(imagePath);
        carRepository.save(car);
    }
    }

