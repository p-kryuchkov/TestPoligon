package api;

import static api.Specifications.getSpecifications;
import static api.Specifications.login;
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
