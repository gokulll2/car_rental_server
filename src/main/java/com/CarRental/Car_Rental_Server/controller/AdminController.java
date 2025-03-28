package com.CarRental.Car_Rental_Server.controller;

import com.CarRental.Car_Rental_Server.dto.CarDto;
import com.CarRental.Car_Rental_Server.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/car")
    public ResponseEntity<?> postCar(@ModelAttribute CarDto carDto) throws IOException {
       boolean success = adminService.postCar(carDto);
       if(success)
       {
          return ResponseEntity.status(HttpStatus.CREATED).build();
       }
       else{
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
       }
    }

    @GetMapping("/cars")
    public ResponseEntity<?> getAllCars(){
        return ResponseEntity.ok(adminService.getAllCars());
    }

    @DeleteMapping("/car/{id}")
   public ResponseEntity<Void> deleteCar(@PathVariable Long id)
    {
        adminService.DeleteCar(id);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<CarDto> getCarById(@PathVariable Long id)
    {
        CarDto carDto = adminService.getCarById(id);
        return ResponseEntity.ok(carDto);
    }

    @PutMapping("/car/{carId}")
    ResponseEntity<Void> updateCar(@PathVariable Long carId , @ModelAttribute CarDto carDto) throws IOException
    {
        try{
            boolean success = adminService.updateCar(carId , carDto);
            if(success)
            {
                return ResponseEntity.status(HttpStatus.OK).build();
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
