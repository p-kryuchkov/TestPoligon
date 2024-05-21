package api;

import com.google.gson.JsonObject;
import dao.EngineTypeDAO;
import entities.Car;
import entities.EngineType;

import java.util.Map;

import static api.Login.getToken;
import static api.Specifications.car;
import static api.Specifications.getSpecifications;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CarMethods {
    public static Car getCarById(long id) {
        getSpecifications();

        Response response = RestAssured.given()
                .log().all()
                .header("Authorization", "Bearer " + getToken())
                .when()
                .get(car + id);



        String engineTypeString = response.jsonPath().getString("engineType");
        EngineType engineType = EngineTypeDAO.getByName(engineTypeString);
        String mark = response.jsonPath().getString("mark");
        String model = response.jsonPath().getString("model");
        Float price = response.jsonPath().getFloat("price");
        Long carId = response.jsonPath().getLong("id");
Car responseCar = new Car(engineType,mark,model,price);
responseCar.setId(carId);
        System.out.println(response.toString());
        return responseCar;
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
