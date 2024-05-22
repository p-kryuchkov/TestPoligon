package api;

import entities.Person;

import static api.Login.getToken;
import static api.Specifications.getSpecifications;
import static api.Specifications.person;
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
        if (response.getSex().equals("MALE")) response.setMale(true);
        else response.setMale(false);
        System.out.println(response.toString());
        return response;
    }

    public static Person createPerson(Person requestPerson){
        getSpecifications();
        Person response = given()
                .log().all()
                .header("Authorization", "Bearer " + getToken())
                .body(requestPerson)
                .post(person)
                .then()
                .statusCode(201)
                .extract()
                .body().as(Person.class);
        System.out.println(response.toString());
        if (response.getSex().equals("MALE")) response.setMale(true);
        else response.setMale(false);
        return response;
    }

    public static Person buyCar(long personId, long carId){
        getSpecifications();
        Person response = given()
                .log().all()
                .header("Authorization", "Bearer " + getToken())
                .post(person + personId + "/buyCar/" + carId)
                .then()
                .statusCode(200)
                .extract()
                .body().as(Person.class);
        System.out.println(response.toString());
        return response;
    }
}

