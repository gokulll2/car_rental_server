package com.CarRental.Car_Rental_Server.dto;

import com.CarRental.Car_Rental_Server.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {

    private long id;

    private String name;

    private String email;


    private UserRole userRole;
}
