package com.CarRental.Car_Rental_Server.dto;

import lombok.Data;
import com.CarRental.Car_Rental_Server.enums.UserRole;
@Data
public class AuthenticationResponse {

   private String jwt;

   private UserRole UserRole;

   private Long userId;

}
