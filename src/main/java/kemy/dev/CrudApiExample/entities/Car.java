package kemy.dev.CrudApiExample.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
write a Spring Boot application with the necessary dependencies that:
has an entity called Car with the following columns:
an id
a modelName
a type
has a dedicated repository for the Car
has a dedicated controller for the Car that:
is mapped on cars
executes the following CRUD operations:
* create a new Car
* return a list of all the Cars
* return a single Car - if the id is not in the db (use existsById()),
* returns an empty Car
* updates the type of a specific Car, identified by id and passing a query param - if not present in the db, returns an empty Car
* deletes a specific Car - if absent, the response will have a Conflict HTTP status
* deletes all the Cars in the db
test the endpoints using Postman for:
* creating 2 different cars
* retrieving all the cars
* retrieving a car by the id
* trying to retrieve an absent car
* updates the type of a specific Car
* trying to update an absent car
* deleting a specific Car
* trying to delete an absent Car
* deleting all the db
*/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "Cars")
public class Car {
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    private String modelName;
    private String brandType;

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
