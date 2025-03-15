package com.CarRental.Car_Rental_Server.service.customer;

import com.CarRental.Car_Rental_Server.dto.CarDto;
import com.CarRental.Car_Rental_Server.entity.Car;
import com.CarRental.Car_Rental_Server.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerSvcImpl implements CustomerService {

    private final CarRepository carRepository;

    @Override
    public List<CarDto> getAllCars() {
        return carRepository.findAll().stream().map(Car::getCarDto).collect(Collectors.toList());
    }
}
