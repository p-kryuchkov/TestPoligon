package api;

import dao.DAO;
import entities.Car;
import entities.EngineType;
import entities.House;

import java.text.DecimalFormat;
import java.util.Random;

import static api.Login.getToken;
import static api.Specifications.getSpecifications;
import static api.Specifications.house;
import static io.restassured.RestAssured.given;
import static java.lang.Math.round;

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

    public static House settle(Long houseId, Long personId) {
        getSpecifications();
        House response = given()
                .log().all()
                .header("Authorization", "Bearer " + getToken())
                .post(house + houseId + "/settle/" + personId)
                .then()
                .statusCode(200)
                .extract()
                .body().as(House.class);
        System.out.println(response.toString());
        return response;
    }
       public static House createRandomHouse() {
        Random r = new Random();
        DecimalFormat df = new DecimalFormat("#.##");
        Float price = (float) (round(r.nextFloat() * 1000000) / 100.0);
        int randomFloorCount = r.nextInt(6 + 1);
        House randomHouse = new House(randomFloorCount,price);
        return randomHouse;
    }
}
