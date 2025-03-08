package com.CarRental.Car_Rental_Server.repository;

import com.CarRental.Car_Rental_Server.entity.User;
import com.CarRental.Car_Rental_Server.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User , Long> {


     Optional<User> findFirstByEmail(String email);

     User findByUserRole(UserRole userRole);
}
