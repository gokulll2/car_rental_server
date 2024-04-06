package com.CarRental.Car_Rental_Server.service.auth;

import com.CarRental.Car_Rental_Server.dto.SignupRequest;
import com.CarRental.Car_Rental_Server.dto.UserDto;
import com.CarRental.Car_Rental_Server.entity.User;
import com.CarRental.Car_Rental_Server.enums.UserRole;
import com.CarRental.Car_Rental_Server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;

    @Override
    public UserDto createCustomer(SignupRequest signupRequest) {
        User user = new User();
        user.setName(signupRequest.getName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(signupRequest.getPassword());
        user.setUserRole(UserRole.Customer);
        User createdUser = userRepository.save(user);
        UserDto userDto = new UserDto();
        userDto.setId(createdUser.getId());
        return userDto;
    }

    @Override
    public boolean hasCustomerWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }
}
