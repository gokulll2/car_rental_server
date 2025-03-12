package com.CarRental.Car_Rental_Server.controller;

import com.CarRental.Car_Rental_Server.dto.AuthenticationRequest;
import com.CarRental.Car_Rental_Server.dto.AuthenticationResponse;
import com.CarRental.Car_Rental_Server.dto.SignupRequest;
import com.CarRental.Car_Rental_Server.dto.UserDto;
import com.CarRental.Car_Rental_Server.entity.User;
import com.CarRental.Car_Rental_Server.repository.UserRepository;
import com.CarRental.Car_Rental_Server.service.auth.AuthService;
import com.CarRental.Car_Rental_Server.service.jwt.UserService;
import com.CarRental.Car_Rental_Server.utils.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final JWTUtil jwtUtil;

    private final UserRepository userRepository;


    @PostMapping("/signup")
    private ResponseEntity<?> signUpCustomer(@RequestBody SignupRequest signupRequest){
        if(authService.hasCustomerWithEmail(signupRequest.getEmail()))
        {
            return new ResponseEntity<>("Customer already exists with this email" ,HttpStatus.NOT_ACCEPTABLE);
        }
       UserDto createdCustomerDTO = authService.createCustomer(signupRequest);
      if(createdCustomerDTO==null)
      {
          return new ResponseEntity<>("Customer not created" , HttpStatus.BAD_REQUEST);
      }
      return new ResponseEntity<>(createdCustomerDTO , HttpStatus.CREATED);
    }

    @PostMapping("/login")
    private AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws
            BadCredentialsException,
            DisabledException,
            UsernameNotFoundException
    {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
                    authenticationRequest.getPassword()));
        }
        catch(BadCredentialsException e)
        {
            throw new BadCredentialsException("Incorrect Username or Password");
        }
        final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(authenticationRequest.getEmail());
        Optional<User> OptionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        if(OptionalUser.isPresent())
        {
            authenticationResponse.setJwt(jwt);
            authenticationResponse.setUserId(OptionalUser.get().getId());
            authenticationResponse.setUserRole(OptionalUser.get().getUserRole());
        }
       return authenticationResponse;
    }

}
