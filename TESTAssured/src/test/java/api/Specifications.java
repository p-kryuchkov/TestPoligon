package api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Specifications {
    Properties properties = new Properties();

    public static RequestSpecification requestSpecification() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("src/test/resources/project.properties"));
        return new RequestSpecBuilder()
                .setBaseUri(properties.getProperty("baseURL"))
                .setContentType(ContentType.JSON)
                .build();
    }
    public static ResponseSpecification responseSpecification(){
        return new ResponseSpecBuilder()
             //   .expectContentType(ContentType.JSON)
             //   .expectStatusCode(200)
                .build();
    }
public static void getSpecifications() throws IOException {
    RestAssured.requestSpecification = requestSpecification();
    RestAssured.responseSpecification = responseSpecification();
}
}
