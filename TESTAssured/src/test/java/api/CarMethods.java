package api;

import dao.EngineTypeDAO;
import entities.Car;
import entities.EngineType;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static api.Login.getToken;
import static api.Specifications.car;
import static api.Specifications.getSpecifications;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.fail;

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
        Car responseCar = new Car(engineType, mark, model, price);
        responseCar.setId(carId);
        System.out.println(response.toString());
        return responseCar;
    }
    public static Car createCar(Car requestCar) {
        getSpecifications();
        String requestBody =
                "{\"engineType\": \"" + requestCar.getEngineType().getType_name() + "\", " +
                        "\"mark\": \"" + requestCar.getMark() + "\", " +
                        "\"model\": \"" + requestCar.getModel() + "\", " +
                        "\"price\": " + requestCar.getPrice() +
                        "}";

        Response response = RestAssured.given()
                .log().all()
                .header("Authorization", "Bearer " + getToken())
                .body(requestBody)
                .post(car);
        if (response.getStatusCode() == 201){
            String engineTypeString = response.jsonPath().getString("engineType");
            EngineType engineType = EngineTypeDAO.getByName(engineTypeString);
            String mark = response.jsonPath().getString("mark");
            String model = response.jsonPath().getString("model");
            Float price = response.jsonPath().getFloat("price");
            Long carId = response.jsonPath().getLong("id");
            Car responseCar = new Car(engineType, mark, model, price);
            responseCar.setId(carId);
            System.out.println(response.toString());
            return responseCar;
        } else fail("Статус-код ответа должен быть 201");
                return null;
    }
}
