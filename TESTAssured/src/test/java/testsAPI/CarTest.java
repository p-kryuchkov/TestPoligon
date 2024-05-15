package testsAPI;

import api.Login;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static api.Login.getToken;
import static io.restassured.RestAssured.given;

public class CarTest {
    @Test
public void login() throws IOException {
        given().
              baseUri("http://77.50.236.203:4880").
              log().all()
              .param("username","user@pflb.ru")
              .param("password", "user")
              .post("login")
              .body().prettyPrint();
String token = getToken();
}
}
