package com.CarRental.Car_Rental_Server.service.customer;

import com.CarRental.Car_Rental_Server.dto.CarDto;

import java.util.List;

public interface CustomerService {

    List<CarDto> getAllCars();
}
