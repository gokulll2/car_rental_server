package com.CarRental.Car_Rental_Server.service.customer;

import com.CarRental.Car_Rental_Server.dto.BookACarDto;
import com.CarRental.Car_Rental_Server.dto.CarDto;

import java.util.List;

public interface CustomerService {

    List<CarDto> getAllCars();

    boolean bookACar(BookACarDto bookACarDto);

    CarDto getCarByID(Long carId);
}
