package com.CarRental.Car_Rental_Server.controller;

import com.CarRental.Car_Rental_Server.dto.SignupRequest;
import com.CarRental.Car_Rental_Server.dto.UserDto;
import com.CarRental.Car_Rental_Server.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("/signup")
    private ResponseEntity<?> signUpCustomer(@RequestBody SignupRequest signupRequest){
        if(authService.hasCustomerWithEmail(signupRequest.getEmail()))
        {
            return new ResponseEntity<>("Customer already exists with this email" ,HttpStatus.NOT_ACCEPTABLE);
        }
       UserDto createdCustomerDTO = authService.createCustomer(signupRequest);
      if(createdCustomerDTO==null)
      {
          return new ResponseEntity<>("Customer not created" , HttpStatus.BAD_REQUEST);
      }
      return new ResponseEntity<>(createdCustomerDTO , HttpStatus.CREATED);
    }
}
