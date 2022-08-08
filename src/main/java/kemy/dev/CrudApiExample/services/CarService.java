package kemy.dev.CrudApiExample.services;

import kemy.dev.CrudApiExample.entities.Car;
import kemy.dev.CrudApiExample.repositories.CarRepository;
import lombok.Data;
import lombok.ToString;
import net.bytebuddy.dynamic.DynamicType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Data
@ToString
@Service
public class CarService {
    @Autowired
    CarRepository carRepository;
    @Autowired
    Optional <Car> car ;



    //CREATE
    public Car createCar(@RequestBody Car car){
        return carRepository.save(car);
    }
    //READ
    public List<Car> getAllCars(){
        return carRepository.findAll();
    }

    //return a single Car

  /*  public Car getSingleCar(@PathVariable long id){
        Car car = carRepository.getReferenceById(id);
        boolean existsById = carRepository.existsById(id);
        if(existsById){
            //return a single Car - if the id is not in the db
            return car;
        }
        //returns an empty Car
        return new Car();
    }

   */


  public Optional<Car> getSingleCar(@PathVariable long id){

             if(carRepository.existsById(id)){

             return carRepository.findById(id);}
             else {
                 System.out.println("Requested car id: "+id+". Car id not found");  //write in console "Not found!"
                 car= Optional.of(new Car());
                 return car;
             }
    }
    //UPDATEc
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
