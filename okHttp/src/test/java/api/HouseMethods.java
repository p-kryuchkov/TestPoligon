package api;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import entities.House;
import entities.ParkingPlace;
import entities.Person;
import okhttp3.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

public class HouseMethods extends ApiMethods {
    private final OkHttpClient client;

    public HouseMethods() {
        this.client = new OkHttpClient();
    }

    public static House parseJsonToHouse(String response) {
        Gson gson = new Gson();
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
        responseHouse.setId(houseId);
        return responseHouse;
    }

    public static String parseHouseToJson(House requestHouse) {
        return new Gson().toJson(requestHouse);
//        Gson gson = new Gson();
//        String jsonParkingPlaces = gson.toJson(requestHouse.getParkingPlaces());
//        JsonObject jsonObject = new JsonObject();
//        jsonObject.addProperty("floorCount", requestHouse.getFloorCount());
//        jsonObject.addProperty("price", requestHouse.getPrice());
//        jsonObject.add("parkingPlaces", JsonParser.parseString(jsonParkingPlaces));
//        return jsonObject.toString();
    }

    public static House createRandomHouse() {
        Random r = new Random();
        DecimalFormat df = new DecimalFormat("#.##");
        Float price = (float) Math.round(r.nextFloat() * 1000000) / 100.0f;
        int randomFloorCount = r.nextInt(6) + 1;
        House randomHouse = new House(randomFloorCount, price);
        return randomHouse;
    }
}
