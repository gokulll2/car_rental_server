package com.CarRental.Car_Rental_Server.repository;

import com.CarRental.Car_Rental_Server.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car , Long> {

}
