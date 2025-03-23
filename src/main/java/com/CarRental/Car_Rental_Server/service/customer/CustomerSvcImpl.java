package com.CarRental.Car_Rental_Server.service.customer;

import com.CarRental.Car_Rental_Server.dto.BookACarDto;
import com.CarRental.Car_Rental_Server.dto.CarDto;
import com.CarRental.Car_Rental_Server.entity.BookACar;
import com.CarRental.Car_Rental_Server.entity.Car;
import com.CarRental.Car_Rental_Server.entity.User;
import com.CarRental.Car_Rental_Server.enums.BookCarStatus;
import com.CarRental.Car_Rental_Server.repository.BookACarRepository;
import com.CarRental.Car_Rental_Server.repository.CarRepository;
import com.CarRental.Car_Rental_Server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerSvcImpl implements CustomerService {

    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final BookACarRepository bookACarRepository;

    @Override
    public List<CarDto> getAllCars() {
        return carRepository.findAll().stream().map(Car::getCarDto).collect(Collectors.toList());
    }

    @Override
    public boolean bookACar(BookACarDto bookACarDto) {
        Optional<Car> optionalCar = carRepository.findById(bookACarDto.getCar_id());
        Optional<User> optionalUser = userRepository.findById(bookACarDto.getUser_id());

        if(optionalCar.isPresent() && optionalUser.isPresent())
        {
            Car existingCar = optionalCar.get();
            BookACar bookACar = new BookACar();
            bookACar.setCar(existingCar);
            bookACar.setUser(optionalUser.get());
            bookACar.setBookCarStatus(BookCarStatus.PENDING);
            long diffInMilliSeconds = bookACarDto.getToDate().getTime() - bookACarDto.getFromDate().getTime();
            long days = TimeUnit.MICROSECONDS.toDays(diffInMilliSeconds);
            bookACar.setDays(days);
            bookACar.setPrice(existingCar.getPrice() * days);
            bookACarRepository.save(bookACar);
            return true;
        }
        return false;
    }

    @Override
    public CarDto getCarByID(Long carId) {
        Optional<Car> optionalCar = carRepository.findById(carId);
        return optionalCar.map(Car::getCarDto).orElse(null);
    }


}
