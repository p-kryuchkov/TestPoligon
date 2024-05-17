package api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Specifications {
    public static Properties getProperties() {
        try {
            Properties properties = new Properties();
            properties.load(new FileReader("src/test/resources/project.properties"));
            return properties;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static RequestSpecification requestSpecification() {
        Properties properties = getProperties();
        return new RequestSpecBuilder()
                .setBaseUri(properties.getProperty("baseURL"))
                .addHeader("Content-Type", "application/json")
                .build();

    }
    public static ResponseSpecification responseSpecification(){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusCode(201)
                .expectStatusCode(202)

                .build();
    }

    public static void getSpecifications() {
    RestAssured.requestSpecification = requestSpecification();
        // RestAssured.responseSpecification = responseSpecification();
}

    public static final String login = "login";
    public static final String car = "car/";
    public static final String person = "user/";
    public static final String house = "house/";
}
