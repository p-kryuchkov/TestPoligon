package api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.EngineTypeDAO;
import entities.Car;
import entities.EngineType;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;
import java.util.Properties;

import static api.Login.getToken;
import static api.Specifications.*;
import static org.junit.jupiter.api.Assertions.fail;

public class CarMethods {
    public static Car getCarById(long id) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(baseUrl + car + "/" + id);
        httpGet.addHeader("Content-Type", "application/json");
        httpGet.addHeader("Authorization", "Bearer " + getToken());
        System.out.println(httpGet);
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            System.out.println(response.toString());
            String responseBody = EntityUtils.toString(response.getEntity());
            if (responseBody == null) fail("Ответ не получен");
            Gson gson = new Gson();
            try {
                JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);
                String engineTypeString = jsonObject.get("engineType").getAsString();
                EngineType engineType = EngineTypeDAO.getByName(engineTypeString);
                String mark = jsonObject.get("mark").getAsString();
                String model = jsonObject.get("model").getAsString();
                Float price = jsonObject.get("price").getAsFloat();
                Long carId = jsonObject.get("id").getAsLong();
                Car responseCar = new Car(engineType, mark, model, price);
                responseCar.setId(carId);
                System.out.println(responseCar.toString());
                return responseCar;
            }
            catch (NullPointerException e){
                fail("Ответ пустой");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public static Car createCar(Car requestCar) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(baseUrl + car);
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.addHeader("Accept", "*/*");
        httpPost.addHeader("Authorization", "Bearer " + getToken());
        String request = "{\"engineType\": \"" + requestCar.getEngineType().getType_name() + "\", " +
                "\"mark\": \"" + requestCar.getMark() + "\", " +
                "\"model\": \"" + requestCar.getModel() + "\", " +
                "\"price\": " + requestCar.getPrice() +
                "}";
        StringEntity entity = new StringEntity(request, ContentType.APPLICATION_JSON);
        httpPost.setEntity(entity);
        System.out.println(httpPost + request);
        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            System.out.println(response.toString());
            String responseBody = EntityUtils.toString(response.getEntity());
            if (responseBody == null) fail("Ответ не получен");
            Gson gson = new Gson();
            try {
                JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);
                String engineTypeString = jsonObject.get("engineType").getAsString();
                EngineType engineType = EngineTypeDAO.getByName(engineTypeString);
                String mark = jsonObject.get("mark").getAsString();
                String model = jsonObject.get("model").getAsString();
                Float price = jsonObject.get("price").getAsFloat();
                Long carId = jsonObject.get("id").getAsLong();
                Car responseCar = new Car(engineType, mark, model, price);
                responseCar.setId(carId);
                System.out.println(responseCar.toString());
                return responseCar;
            }
            catch (NullPointerException e){
                fail("Ответ пустой");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
