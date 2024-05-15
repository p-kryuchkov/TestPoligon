package api;

import io.restassured.response.Response;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class Login {
    public static String getToken() throws IOException {
        Response response = given().
                baseUri("http://77.50.236.203:4880").
                log().all()
                .param("username","user@pflb.ru")
                .param("password", "user")
                .post("login");


        String token = response.jsonPath().getString("access_token");
        System.out.println("Access Token: " + token);

        return token;
    }
}
