package com.hamza.Car_Sales_Garage;

import com.hamza.Car_Sales_Garage.controller.CarController;
import com.hamza.Car_Sales_Garage.dto.CarDTO;
import com.hamza.Car_Sales_Garage.service.CarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CarController.class)
@AutoConfigureMockMvc
class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;

    @Test
    void addCar_ReturnsCreated() throws Exception {
        CarDTO carDTO = new CarDTO();
        carDTO.setRegistrationDate(LocalDate.of(2016, 1, 1));

        given(carService.addCar(any())).willReturn(carDTO);

        mockMvc.perform(post("/api/car/add-car")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"registrationDate\": \"2016-01-01\" }"))
                .andExpect(status().isCreated());
    }

    @Test
    void getCarsByFuelTypeAndMaxPrice_ReturnsOk() throws Exception {
        given(carService.getCarsByFuelTypeAndMaxPrice(any(), any()))
                .willReturn(Arrays.asList(new CarDTO()));

        mockMvc.perform(get("/api/car/fuel/DIESEL/price/20000"))
                .andExpect(status().isOk());
    }

    @Test
    void getAvailableMakes_ReturnsOk() throws Exception {
        given(carService.getAvailableMakes()).willReturn(Arrays.asList("Toyota", "Honda"));

        mockMvc.perform(get("/api/car/makes"))
                .andExpect(status().isOk());
    }

    @Test
    void updateCarPicture_ReturnsOk() throws Exception {
        Long carId = 1L;
        String imagePath = "image.jpg";

        mockMvc.perform(put("/api/car/{carId}/picture", carId)
                        .param("imagePath", imagePath))
                .andExpect(status().isOk());
    }
}

