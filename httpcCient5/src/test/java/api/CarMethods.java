package api;

import dao.EngineTypeDAO;
import entities.Car;
import entities.EngineType;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;

import java.util.Properties;

import static api.Login.getToken;
import static api.Specifications.*;
import static org.junit.jupiter.api.Assertions.fail;

public class CarMethods {

}
