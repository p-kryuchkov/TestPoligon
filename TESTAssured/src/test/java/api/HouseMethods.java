package api;

import entities.Car;
import entities.House;

import static api.Login.getToken;
import static api.Specifications.*;
import static io.restassured.RestAssured.given;

public class HouseMethods {
    public static House getHouseById(long id) {
        getSpecifications();
        House response = given()
                .log().all()
                .header("Authorization", "Bearer " + getToken())
                .when()
                .get(house + id)
                .then()
                .extract()
                .body()
                .as(House.class);
        System.out.println(response.toString());
        return response;
    }
    public static House createHouse(House requestHouse) {
        getSpecifications();
        House response = given()
                .log().all()
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer " + getToken())
                .body(requestHouse)
                .post(house)
                .then()
                .statusCode(201)
                .extract()
                .body().as(House.class);
        System.out.println(response.toString());
        return response;
    }
}
