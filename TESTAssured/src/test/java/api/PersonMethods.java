package api;

import entities.Car;
import entities.Person;

import static api.Login.getToken;
import static api.Specifications.*;
import static io.restassured.RestAssured.given;


public class PersonMethods {
    public static Person getPersonByID(long id){
        getSpecifications();
        Person response = given()
                .log().all()
                .header("Authorization", "Bearer " + getToken())
                .when()
                .get(person + id)
                .then()
                .extract()
                .body()
                .as(Person.class);
        System.out.println(response.toString());
        return response;
    }
    public static Person createPerson(Person requestPerson){
        getSpecifications();
        Person response = given()
                .log().all()
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer " + getToken())
                .body(requestPerson)
                .post(person)
                .then()
                .statusCode(201)
                .extract()
                .body().as(Person.class);
        System.out.println(response.toString());
        return response;
    }
    }

