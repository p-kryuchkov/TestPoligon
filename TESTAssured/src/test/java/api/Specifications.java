package api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.fail;

public class Specifications {
    public static Properties getProperties() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("src/test/resources/project.properties"));
        } catch (IOException e) {
            fail ("Файл project.properties не найден");
        }
        return properties;
    }

    public static void getSpecifications() {
        Properties properties = getProperties();
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri(properties.getProperty("baseURL"))
                .addHeader("Content-Type", "application/json")
                .build();
        RestAssured.requestSpecification = requestSpecification;
}

    public static final String login = "login";
    public static final String car = "car/";
    public static final String person = "user/";
    public static final String house = "house/";
}
