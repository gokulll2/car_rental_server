package com.CarRental.Car_Rental_Server.dto;

import jakarta.persistence.Column;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class CarDto {

    private Long id;

    private String brand;

    private String color;

    private String description;

    private String name;

    private String type;

    private String transmission;

    private Long price;

    private Date year;

    private MultipartFile image;

    private byte[] returnedImage;
}
