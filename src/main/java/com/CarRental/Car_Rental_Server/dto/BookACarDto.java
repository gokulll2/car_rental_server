package com.CarRental.Car_Rental_Server.dto;

import com.CarRental.Car_Rental_Server.enums.BookCarStatus;
import lombok.Data;

import java.util.Date;

@Data
public class BookACarDto {

    private Long id;

    private Date fromDate;

    private Date toDate;

    private Long days;

    private Long price;

    private BookCarStatus bookCarStatus;

    private Long car_id;

    private Long user_id;
}
