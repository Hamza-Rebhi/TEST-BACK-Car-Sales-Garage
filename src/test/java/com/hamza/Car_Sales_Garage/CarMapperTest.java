package com.hamza.Car_Sales_Garage;

import com.hamza.Car_Sales_Garage.dto.CarDTO;
import com.hamza.Car_Sales_Garage.entities.Car;
import com.hamza.Car_Sales_Garage.entities.FuelType;
import com.hamza.Car_Sales_Garage.entities.TransmissionType;
import com.hamza.Car_Sales_Garage.mappers.CarMapper;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CarMapperTest {

    @Test
    void mapCarToCarDTOSuccess() {
        // Créer une instance de Car
        Car car = new Car();
        car.setId(1L);
        car.setMake("Toyota");
        car.setModel("Corolla");
        car.setRegistrationDate(LocalDate.of(2022, 1, 1));
        car.setPrice(BigDecimal.valueOf(20000));
        car.setFuelType(FuelType.DIESEL);
        car.setMileage(50000);
        car.setTransmission(TransmissionType.AUTOMATIC);
        car.setImagePath("corolla_image.jpg");

        // Mapper l'objet Car en CarDTO
        CarDTO carDTO = CarMapper.INSTANCE.mapCarToCarDTO(car);

        // Assertions
        assertEquals(car.getId(), carDTO.getId());
        assertEquals(car.getMake(), carDTO.getMake());
        assertEquals(car.getModel(), carDTO.getModel());
        assertEquals(car.getRegistrationDate(), carDTO.getRegistrationDate());
        assertEquals(car.getPrice(), carDTO.getPrice());
        assertEquals(car.getFuelType(), carDTO.getFuelType());
        assertEquals(car.getMileage(), carDTO.getMileage());
        assertEquals(car.getTransmission(), carDTO.getTransmission());
        assertEquals(car.getImagePath(), carDTO.getImagePath());
    }

    @Test
    void mapCarDTOToCarSuccess() {
        // Créer une instance de CarDTO
        CarDTO carDTO = new CarDTO();
        carDTO.setId(1L);
        carDTO.setMake("Toyota");
        carDTO.setModel("Corolla");
        carDTO.setRegistrationDate(LocalDate.of(2022, 1, 1));
        carDTO.setPrice(BigDecimal.valueOf(20000));
        carDTO.setFuelType(FuelType.DIESEL);
        carDTO.setMileage(50000);
        carDTO.setTransmission(TransmissionType.AUTOMATIC);
        carDTO.setImagePath("corolla_image.jpg");

        // Mapper l'objet CarDTO en Car
        Car car = CarMapper.INSTANCE.mapCarDTOToCar(carDTO);

        // Assertions
        assertEquals(carDTO.getId(), car.getId());
        assertEquals(carDTO.getMake(), car.getMake());
        assertEquals(carDTO.getModel(), car.getModel());
        assertEquals(carDTO.getRegistrationDate(), car.getRegistrationDate());
        assertEquals(carDTO.getPrice(), car.getPrice());
        assertEquals(carDTO.getFuelType(), car.getFuelType());
        assertEquals(carDTO.getMileage(), car.getMileage());
        assertEquals(carDTO.getTransmission(), car.getTransmission());
        assertEquals(carDTO.getImagePath(), car.getImagePath());
    }
}

