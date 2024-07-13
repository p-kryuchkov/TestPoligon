package api;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import entities.House;
import entities.ParkingPlace;
import entities.Person;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

import static java.lang.Math.round;


public class HouseMethods extends ApiMethods {
    public HouseMethods() {
    }

    public static House parseJsonToHouse(String response) {
        JsonObject jsonObject = gson.fromJson(response, JsonObject.class);
        int floorCount = jsonObject.get("floorCount").getAsInt();
        Float price = jsonObject.get("price").getAsFloat();
        JsonArray arrayParkingPlaces = jsonObject.get("parkingPlaces").getAsJsonArray();
        Type listType = new TypeToken<List<ParkingPlace>>() {
        }.getType();
        List<ParkingPlace> parkingPlaces = gson.fromJson(arrayParkingPlaces, listType);
        JsonArray arrayLodgers = jsonObject.get("lodgers").getAsJsonArray();
        Type lodgersListType = new TypeToken<List<Person>>() {
        }.getType();
        List<Person> persons = gson.fromJson(arrayLodgers, lodgersListType);
        Long houseId = jsonObject.get("id").getAsLong();
        House responseHouse = new House(floorCount, price, parkingPlaces, persons);
        System.out.println(responseHouse.toString());
        responseHouse.setId(houseId);
        System.out.println(responseHouse.toString());
        return responseHouse;
    }

    public static String parseHouseToJson(House requestHouse) {
        List<ParkingPlace> parkingPlacesJson = requestHouse.getParkingPlaces();
        Gson gsonJson = new Gson();
        String jsonArray = gsonJson.toJson(parkingPlacesJson);
        String request = "{\"floorCount\": \"" + requestHouse.getFloorCount() + "\", " +
                "\"price\": \"" + requestHouse.getPrice() + "\", " +
                "\"parkingPlaces\": " + jsonArray +
                "}";
        return request;
    }

    public static House createRandomHouse() {
        Random r = new Random();
        DecimalFormat df = new DecimalFormat("#.##");
        Float price = (float) (round(r.nextFloat() * 1000000) / 100.0);
        int randomFloorCount = r.nextInt(6 + 1);
        House randomHouse = new House(randomFloorCount, price);
        return randomHouse;
    }
}
