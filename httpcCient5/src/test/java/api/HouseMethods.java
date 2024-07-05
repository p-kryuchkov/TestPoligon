package api;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import dao.EngineTypeDAO;
import entities.*;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.lang.reflect.Type;
import java.util.List;

import static api.Login.getToken;
import static api.Specifications.*;
import static org.junit.jupiter.api.Assertions.fail;


public class HouseMethods {
    public static House getHouseById(long id) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(baseUrl + house + "/" + id);
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
                int floorCount = jsonObject.get("floorCount").getAsInt();
                Float price = jsonObject.get("price").getAsFloat();
                JsonArray arrayParkingPlaces = jsonObject.get("parkingPlaces").getAsJsonArray();
                Type listType = new TypeToken<List<ParkingPlace>>() {}.getType();
                List <ParkingPlace> parkingPlaces = gson.fromJson(arrayParkingPlaces, listType);
//                JsonArray arrayLodgers = jsonObject.get("lodgers").getAsJsonArray();
//                Type lodgersListType = new TypeToken<List<Person>>() {}.getType();
//                List <Person> persons = gson.fromJson(arrayLodgers, lodgersListType);
                Long houseId = jsonObject.get("id").getAsLong();
                House responseHouse = new House(floorCount, price,parkingPlaces);
                responseHouse.setId(houseId);
                System.out.println(responseHouse.toString());
                return responseHouse;
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

    public static House createHouse(House requestHouse) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(baseUrl + house + "/");
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.addHeader("Accept", "*/*");
        httpPost.addHeader("Authorization", "Bearer " + getToken());
        List <ParkingPlace> parkingPlacesJson = requestHouse.getParkingPlaces();
        Gson gsonJson = new Gson();
        String jsonArray = gsonJson.toJson(parkingPlacesJson);
        String request = "{\"floorCount\": \"" + requestHouse.getFloorCount() + "\", " +
                "\"price\": \"" + requestHouse.getPrice() + "\", " +
                "\"parkingPlaces\": " + jsonArray +
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
                int floorCount = jsonObject.get("floorCount").getAsInt();
                Float price = jsonObject.get("price").getAsFloat();
                JsonArray arrayParkingPlaces = jsonObject.get("parkingPlaces").getAsJsonArray();
                Type listType = new TypeToken<List<ParkingPlace>>() {}.getType();
                List <ParkingPlace> parkingPlaces = gson.fromJson(arrayParkingPlaces, listType);
//                JsonArray arrayLodgers = jsonObject.get("lodgers").getAsJsonArray();
//                Type lodgersListType = new TypeToken<List<Person>>() {}.getType();
//                List <Person> persons = gson.fromJson(arrayLodgers, lodgersListType);
                Long houseId = jsonObject.get("id").getAsLong();
                House responseHouse = new House(floorCount, price,parkingPlaces);
                responseHouse.setId(houseId);
                System.out.println(responseHouse.toString());
                return responseHouse;
            }
            catch (NullPointerException e){
                fail("Ответ  пустой");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }


    public static House settle(Long houseId, Long personId) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(baseUrl + house + "/" + houseId + "/settle/" + personId);
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.addHeader("Accept", "*/*");
        httpPost.addHeader("Authorization", "Bearer " + getToken());
        System.out.println(httpPost);
        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            System.out.println(response.toString());
            String responseBody = EntityUtils.toString(response.getEntity());
            if (responseBody == null) fail("Ответ не получен");
            Gson gson = new Gson();
            try {
                JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);
                int floorCount = jsonObject.get("floorCount").getAsInt();
                Float price = jsonObject.get("price").getAsFloat();
                JsonArray arrayParkingPlaces = jsonObject.get("parkingPlaces").getAsJsonArray();
                Type listType = new TypeToken<List<ParkingPlace>>() {}.getType();
                List <ParkingPlace> parkingPlaces = gson.fromJson(arrayParkingPlaces, listType);
//                JsonArray arrayLodgers = jsonObject.get("lodgers").getAsJsonArray();
//                Type lodgersListType = new TypeToken<List<Person>>() {}.getType();
//                List <Person> persons = gson.fromJson(arrayLodgers, lodgersListType);
                Long houseIdJson = jsonObject.get("id").getAsLong();
                House responseHouse = new House(floorCount, price,parkingPlaces);
                responseHouse.setId(houseIdJson);
                System.out.println(responseHouse.toString());
                return responseHouse;
            }
            catch (NullPointerException e){
                fail("Ответ  пустой");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
        }

