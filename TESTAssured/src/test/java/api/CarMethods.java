package api;

import entities.Car;

import static api.Login.getToken;
import static api.Specifications.car;
import static api.Specifications.getSpecifications;
import static io.restassured.RestAssured.given;

public class CarMethods {
    public static Car getCarById(long id) {
        getSpecifications();
        Car response = given()
                .log().all()
                .header("Authorization", "Bearer " + getToken())
                .when()
                .get(car + id)
                .then()
                .extract()
                .body()
                .as(Car.class);
        System.out.println(response.toString());
        return response;
    }
    public static Car createCar(Car requestCar) {
        getSpecifications();
        Car response = given()
                .log().all()
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer " + getToken())
                .body(requestCar)
                .post(car)
                .then()
                .statusCode(201)
                .extract()
                .body().as(Car.class);
        System.out.println(response.toString());
        return response;
    }
}