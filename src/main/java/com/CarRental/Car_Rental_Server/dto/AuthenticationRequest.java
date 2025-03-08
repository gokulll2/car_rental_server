package com.CarRental.Car_Rental_Server.dto;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String email;

    private String password;
}
