package com.hamza.Car_Sales_Garage;

import com.hamza.Car_Sales_Garage.dto.CarDTO;
import com.hamza.Car_Sales_Garage.entities.Car;
import com.hamza.Car_Sales_Garage.entities.FuelType;
import com.hamza.Car_Sales_Garage.repositories.CarRepository;
import com.hamza.Car_Sales_Garage.service.CarServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarServiceImpTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImp carService;

    @Test
    void addCar_RegistrationDateBefore2016_ThrowsException() {
        CarDTO carDTO = new CarDTO();
        carDTO.setRegistrationDate(LocalDate.of(2015, 12, 31));

        assertThrows(IllegalArgumentException.class, () -> carService.addCar(carDTO));
    }

    @Test
    void addCar_RegistrationDateAfter2015_Success() {
        CarDTO carDTO = new CarDTO();
        carDTO.setRegistrationDate(LocalDate.of(2016, 1, 1));

        when(carRepository.save(any())).thenReturn(new Car());

        CarDTO addedCarDTO = carService.addCar(carDTO);

        assertNotNull(addedCarDTO);
    }

    @Test
    void getCarsByFuelTypeAndMaxPrice_Success() {
        FuelType fuelType = FuelType.DIESEL;
        BigDecimal maxPrice = BigDecimal.valueOf(20000);
        List<Car> cars = new ArrayList<>();
        // Ajouter des voitures à la liste cars

        when(carRepository.findByFuelTypeAndPriceLessThan(fuelType, maxPrice)).thenReturn(cars);

        List<CarDTO> carDTOs = carService.getCarsByFuelTypeAndMaxPrice(fuelType, maxPrice);

        assertEquals(cars.size(), carDTOs.size());
    }

    @Test
    void getAvailableMakes_Success() {
        List<String> makes = new ArrayList<>();
        // Ajouter des marques à la liste makes

        when(carRepository.findDistinctMake()).thenReturn(makes);

        List<String> resultMakes = carService.getAvailableMakes();

        assertEquals(makes.size(), resultMakes.size());
    }

    @Test
    void updateCarPicture_Success() {
        Long carId = 1L;
        String imagePath = "image.jpg";
        Car car = new Car();
        // Initialiser car avec l'ID 1L

        when(carRepository.findById(carId)).thenReturn(java.util.Optional.ofNullable(car));

        assertDoesNotThrow(() -> carService.updateCarPicture(carId, imagePath));
    }
}

