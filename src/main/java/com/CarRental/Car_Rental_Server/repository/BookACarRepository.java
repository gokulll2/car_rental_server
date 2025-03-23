package com.CarRental.Car_Rental_Server.repository;

import com.CarRental.Car_Rental_Server.entity.BookACar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookACarRepository extends JpaRepository<BookACar , Long> {

}
