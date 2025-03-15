package com.CarRental.Car_Rental_Server.service.admin;

import com.CarRental.Car_Rental_Server.dto.CarDto;

import java.io.IOException;
import java.util.List;

public interface AdminService {

    boolean postCar(CarDto carDto) throws IOException;

    List<CarDto> getAllCars();

    void DeleteCar(Long id);

    CarDto getCarById(Long id);

    boolean updateCar(Long carId , CarDto carDto) throws IOException;
}
