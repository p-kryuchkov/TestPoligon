package api;

import java.util.Properties;

import static api.Specifications.*;
import static io.restassured.RestAssured.given;

public class Login {
    private final String username = Specifications.getProperties().getProperty("apiLogin");
    private final String password = Specifications.getProperties().getProperty("apiPassword");
    public static String getToken() {
        Login loginRequest = new Login();
        getSpecifications();
        String token = given()
                .log().all()
                .body(loginRequest)
                .post(login)
                .jsonPath()
                .getString("access_token");
        System.out.println("Access Token: " + token);
        return token;
    }
}
