package kemy.dev.CrudApiExample.services;

import kemy.dev.CrudApiExample.entities.Car;
import kemy.dev.CrudApiExample.repositories.CarRepository;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Data
@ToString
@Service
public class CarService {
    @Autowired
    CarRepository carRepository;

// to run getSingleCar() you must create this attribute
    @Autowired
    Optional <Car> car ;

// to run deleteSingleCar() you must create these attributes:
    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpServletResponse response;


    //CREATE
    public Car createCar(@RequestBody Car car){
        return carRepository.save(car);
    }
    //READ
    public List<Car> getAllCars(){
        return carRepository.findAll();
    }
    // gets a specific Car - if absent, the response will be an empty Car
    public Optional<Car> getSingleCar(@PathVariable long id){

        if(carRepository.existsById(id)){

        return carRepository.findById(id);}
        else {
        System.out.println("Requested car id: "+id+". Car id not found");  //write in console "Not found!"
        car= Optional.of(new Car());
        return car;
        }
    }
    //UPDATE
    // edit a specific Car - if absent, it will create an empty Car
   public Car editCar (@PathVariable long id,@RequestBody Car car) {
       if (carRepository.existsById(id)) {
       car.setId(id);
       return carRepository.save(car);
       } else
       System.out.println("Requested car id: "+id+". Car id not found. Created new empty car");  //write in console "Not found!"
       return carRepository.saveAndFlush(new Car());
    }

   //DELETE

   // deletes a specific Car - if absent, the response will have a Conflict HTTP status
   public String deleteSingleCar(@PathVariable long id) {

        if (!carRepository.existsById(id)) {
            System.out.println(HttpStatus.CONFLICT);
            try {
                response.sendError(409, "CONFLICT");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else
            carRepository.deleteById(id);
            System.out.println("car with  id " + id + " was deleted");
            return "car with  id " + id + " was deleted";

    }

    public String deleteAllCars(){
        carRepository.deleteAll();
        System.out.println("deleted all cars");
        return "deleted all cars";
    }
}
