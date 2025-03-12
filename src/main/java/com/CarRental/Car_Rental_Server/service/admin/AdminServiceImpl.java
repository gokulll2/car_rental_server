package com.CarRental.Car_Rental_Server.service.admin;

import com.CarRental.Car_Rental_Server.dto.CarDto;
import com.CarRental.Car_Rental_Server.entity.Car;
import com.CarRental.Car_Rental_Server.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final CarRepository carRepository;

    @Override
    public boolean postCar(CarDto carDto) throws IOException {
        try{
            Car car = new Car();
            car.setName(carDto.getName());
            car.setColor(carDto.getColor());
            car.setBrand(carDto.getBrand());
            car.setPrice(carDto.getPrice());
            car.setYear(carDto.getYear());
            car.setType(carDto.getType());
            car.setDescription(carDto.getDescription());
            car.setTransmission(carDto.getTransmission());
            car.setImage(carDto.getImage().getBytes());
            carRepository.save(car);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<CarDto> getAllCars() {
       return carRepository.findAll().stream().map(Car::getCarDto).collect(Collectors.toList());
    }

    @Override
    public void DeleteCar(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public CarDto getCarById(Long id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if(optionalCar.isPresent())
        {
            return optionalCar.get().getCarDto();
        }
        else {
            return null;
        }
    }
}
