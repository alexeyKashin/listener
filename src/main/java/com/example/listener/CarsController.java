package com.example.listener;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/car")
public class CarsController {
    private final CarsRepository carsRepository;

    public CarsController(CarsRepository carsRepository) {
        this.carsRepository = carsRepository;
    }

    @GetMapping
    public List<Car> getCars() {
        return carsRepository.findAll();
    }

    @GetMapping("/{id}")
    public Car getCar(@PathVariable Long id) {
        return carsRepository.findById(id).get();
    }

    @PostMapping
    public Car createCar(@RequestBody CarDto carDto) {
        Car car = new Car();
        car.setModel(carDto.getModel());
        car.setColor(carDto.getColor());

        return carsRepository.save(car);
    }

    @PutMapping("/{id}")
    public Car updateCar(@PathVariable long id, @RequestBody CarDto carDto) {
        Car car = carsRepository.findById(id).get();
        car.setModel(carDto.getModel());
        car.setColor(carDto.getColor());

        return carsRepository.save(car);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable long id) {
        carsRepository.deleteById(id);
    }

}
