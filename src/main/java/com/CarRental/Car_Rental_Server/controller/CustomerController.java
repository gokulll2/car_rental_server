package com.CarRental.Car_Rental_Server.controller;

import com.CarRental.Car_Rental_Server.dto.BookACarDto;
import com.CarRental.Car_Rental_Server.dto.CarDto;
import com.CarRental.Car_Rental_Server.service.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/cars")
    public ResponseEntity<List<CarDto>> getAllCars(){
        List<CarDto> carDtoList = customerService.getAllCars();
        return ResponseEntity.ok(carDtoList);
    }

    @PostMapping("/car/book")
    public ResponseEntity<Void> BookACar(@RequestBody BookACarDto bookACarDto)
    {
       boolean success = customerService.bookACar(bookACarDto);
       if(success)
       {
           return ResponseEntity.status(HttpStatus.CREATED).build();
       }
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/car/{carId}")
    public ResponseEntity<CarDto> getCarById(@PathVariable Long carId)
    {
        CarDto carDto = customerService.getCarByID(carId);
        if(carDto==null) return ResponseEntity.notFound().build();
 
        return ResponseEntity.ok(carDto);
    }



}
