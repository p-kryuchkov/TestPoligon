package api;

import entities.Person;

import java.text.DecimalFormat;
import java.util.Random;

import static api.Login.getToken;
import static api.Specifications.getSpecifications;
import static api.Specifications.person;
import static io.restassured.RestAssured.given;
import static java.lang.Math.round;


public class PersonMethods {
    public static Person getPersonByID(long id) {
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

    public static Person createPerson(Person requestPerson) {
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

    public static Person buyCar(long personId, long carId) {
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

    /**
     * Метод принимает в себя длину уникальной строки, которую добавит в Имя/Фамилию и сгенерирует остальные данные
     */
    public static Person createRandomPerson(int length) {
        Random r = new Random();
        char[] charArray = new char[length];
        for (int i = 0; i < length; i++) {
            char c = (char) (r.nextInt(25) + 65);
            charArray[i] = c;
        }
        String randomString = new String(charArray);
        DecimalFormat df = new DecimalFormat("#.##");
        Float money = (float) (round(r.nextFloat() * 1000000) / 100.0);
        Person randomPerson = new Person("firstName" + randomString, "secondName" + randomString, r.nextInt(99) + 1, r.nextBoolean(), money);
        return randomPerson;
    }
}

