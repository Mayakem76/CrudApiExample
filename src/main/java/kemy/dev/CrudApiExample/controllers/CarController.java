package kemy.dev.CrudApiExample.controllers;

import kemy.dev.CrudApiExample.entities.Car;
import kemy.dev.CrudApiExample.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {
    @Autowired
    CarService carService;
    @PostMapping ("")
    public Car createCar(@RequestBody Car car){
        return carService.createCar(car);
    }
    @GetMapping("")
    public List<Car> getAllCars(){
        return carService.getAllCars();
    }
    @GetMapping("/{id}")
    public Optional<Car> getSingleCar(@PathVariable long id){
            return carService.getSingleCar(id);
        }

    @PutMapping ("")
    public Car editCar ( @RequestParam long id ,@RequestBody Car car) {
        car.setId(id);
        return carService.editCar(id, car);
    }
    /* @PutMapping ("/{id}") //Per inserire id nel path e non come parametro
    public Car editCar (@PathVariable long id,@RequestBody Car car) {
        car.setId(id);
        return carService.editCar(id, car);
    }
    */
    @DeleteMapping("/{id}")
    public String deleteSingleCar(@PathVariable long id) {
     return carService.deleteSingleCar(id);
    }
    @DeleteMapping("") //cancella tutte le cars
    public String deleteAllCars (){
        carService.deleteAllCars();
        System.out.println("deleted all cars");
        return "deleted all cars";
    }

  /*  @DeleteMapping("/del") //cancella car con RequestParam
    public String deleteCar( @RequestParam long id){
        carService.deleteCar(id);
        System.out.println("deleted car id: "+id);
        return "deleted car id: "+id;
    }

   */


}
