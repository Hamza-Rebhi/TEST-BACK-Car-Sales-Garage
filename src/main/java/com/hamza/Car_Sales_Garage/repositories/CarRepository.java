package com.hamza.Car_Sales_Garage.repositories;

import com.hamza.Car_Sales_Garage.entities.Car;
import com.hamza.Car_Sales_Garage.entities.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
@Repository
public interface CarRepository  extends JpaRepository<Car, Long> {
    List<Car> findByFuelTypeAndPriceLessThan(FuelType fuelType, BigDecimal maxPrice);
    @Query("SELECT DISTINCT c.make FROM Car c")
    List<String> findDistinctMake();
}
