package com.hamza.Car_Sales_Garage.mappers;

import com.hamza.Car_Sales_Garage.dto.CarDTO;
import com.hamza.Car_Sales_Garage.entities.Car;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarMapper {
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    CarDTO mapCarToCarDTO(Car car);

    Car mapCarDTOToCar(CarDTO carDTO);
}
