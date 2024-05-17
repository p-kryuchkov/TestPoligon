package testsAPI;


import api.CarMethods;
import dao.CarDAO;
import entities.Car;
import io.restassured.RestAssured;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static api.Login.getToken;
import static api.Specifications.car;
import static api.Specifications.getProperties;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CarTest {

    @Test
    @DisplayName("Проверка создания автомобиля")
    public void createCar() {
        int sizeBefore = CarDAO.getAll().size();
        Car requestCar = new Car("Electric", "Moskvitch", "Noviy", 212.3F);
        Car responceCar = CarMethods.createCar(requestCar);
        assertTrue(requestCar.equalsWithotId(responceCar), "Отправленные и полученные данные не совпадают");
        Car daoCar = (Car) Hibernate.unproxy(CarDAO.getByID(responceCar.getId()));
        assertTrue(requestCar.equalsWithotId(daoCar), "Отправленные данные и данные в БД не совпадают");
        int sizeAfter = CarDAO.getAll().size();
        assertEquals(sizeAfter, sizeBefore + 1, "Количество машин не изменилось");
        assertTrue(requestCar.equalsWithotId(CarMethods.getCarById(responceCar.getId())),"При поиске по id находится другая машина");
    }

    @Test
    public void createCarTest() {
        Properties properties = getProperties();
        Car requestCar = new Car("Electric", "Moskvitch", "Noviy", 212.3F);

        RestAssured.given()
                .baseUri("http://77.50.236.203:4880")
                .log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + getToken())
                .body(requestCar)
                .post(car)
                .then()
                .statusCode(201)
                .extract()
                .body().as(Car.class);

        //   getSpecifications();
/*

        Car response = given()
                .baseUri("http://77.50.236.203:4880")
                .log().all()
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer " + getToken())
                .param("engineType", "Electric")
                .param("mark", "Zhigul")
                .param("model", "Shest")
                .param("price", 7002.2)
                .post(car)
                .then()
                .statusCode(201)
                .extract()
                .body().as(Car.class);

        System.out.println(response.toString());

         */
/*
        String jsonBody = "{\n" +
                "  \"engineType\": \"Electric\",\n" +
                "  \"mark\": \"Zhigul\",\n" +
                "  \"model\": \"Shest\",\n" +
                "  \"price\": 7002.2\n" +
                "}";

        RestAssured.given()
                .baseUri("http://77.50.236.203:4880")
                .log().all()
                .header("Authorization", "Bearer " + getToken())
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .post(car)
                .then()
                .statusCode(201)
                .extract()
                .body().as(Car.class);

    }
*/
        // private RequestSpecBuilder RequestSpecBuilder() {
        //return null;
    }
}