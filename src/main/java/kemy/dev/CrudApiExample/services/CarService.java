package kemy.dev.CrudApiExample.services;

import kemy.dev.CrudApiExample.entities.Car;
import kemy.dev.CrudApiExample.repositories.CarRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class CarService {
    @Autowired
    CarRepository carRepository;

//CREATE
    public Car createCar(@RequestBody Car car){
        return carRepository.save(car);
    }
//READ
    public List<Car> getAllCars(){
        return carRepository.findAll();
    }

    public Optional<Car> getOneCar(@PathVariable long id){
        return carRepository.findById(id);
    }
//UPDATE
    public Car editCar (@PathVariable long id,@RequestBody Car car){
    car.setId(id);
    return carRepository.save(car);
}
//DELETE
    public String deleteCar(@PathVariable long id){
    carRepository.deleteById(id);
    System.out.println("deleted car id: "+id);
    return "deleted car ( id: "+id+")";
}
    public String deleteAllCars(){
        carRepository.deleteAll();
        System.out.println("deleted all cars");
        return "deleted all cars";
    }
}
