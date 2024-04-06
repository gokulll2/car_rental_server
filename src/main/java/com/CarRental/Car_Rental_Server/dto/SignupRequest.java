package com.CarRental.Car_Rental_Server.dto;

import lombok.Data;

@Data
public class SignupRequest {

    private String name;
    private String email;
    private String password;

}
