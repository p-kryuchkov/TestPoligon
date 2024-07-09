package api;

import dao.DAO;
import dao.EngineTypeDAO;
import entities.Car;
import entities.EngineType;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.text.DecimalFormat;
import java.util.Random;

import static api.Login.getToken;
import static api.Specifications.car;
import static api.Specifications.getSpecifications;
import static java.lang.Math.round;
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
        if (response.getStatusCode() == 201) {
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

    /**
     * Метод принимает в себя длину уникальной строки, которую добавит в Марку/Модель и сгенерирует остальные данные
     */
    public static Car createRandomCar(int length) {
        Random r = new Random();
        char[] charArray = new char[length];
        for (int i = 0; i < length; i++) {
            char c = (char) (r.nextInt(25) + 65);
            charArray[i] = c;
        }
        String randomString = new String(charArray);
        DecimalFormat df = new DecimalFormat("#.##");
        Float price = (float) (round(r.nextFloat() * 1000000) / 100.0);
        DAO engineTypeDao = new DAO<>(EngineType.class);
        long randomETypeId = r.nextInt(engineTypeDao.getAllSize().intValue() + 1);
        EngineType engineType = (EngineType) engineTypeDao.getByID(randomETypeId);
        Car randomCar = new Car(engineType.getType_name(), "mark" + randomString, "model" + randomString, price);
        return randomCar;
    }
}
