package com.CarRental.Car_Rental_Server.service.auth;

import com.CarRental.Car_Rental_Server.dto.SignupRequest;
import com.CarRental.Car_Rental_Server.dto.UserDto;
import com.CarRental.Car_Rental_Server.entity.User;

public interface AuthService {

    UserDto createCustomer(SignupRequest signupRequest);

    boolean hasCustomerWithEmail(String email);
}
