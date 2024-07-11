package api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.DAO;
import dao.EngineTypeDAO;
import entities.Car;
import entities.EngineType;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Random;

public class CarMethods extends ApiMethods {
    private final OkHttpClient client;

    public CarMethods() {
        this.client = new OkHttpClient();
    }

    public static Car parseJsonToCar(String response) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(response, JsonObject.class);
        String engineTypeString = jsonObject.get("engineType").getAsString();
        EngineType engineType = EngineTypeDAO.getByName(engineTypeString);
        String mark = jsonObject.get("mark").getAsString();
        String model = jsonObject.get("model").getAsString();
        Float price = jsonObject.get("price").getAsFloat();
        Long carId = jsonObject.get("id").getAsLong();
        Car responseCar = new Car(engineType, mark, model, price);
        responseCar.setId(carId);
        return responseCar;
    }

    public static String parseCarToJson(Car requestCar) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("engineType", requestCar.getEngineType().getType_name());
        jsonObject.addProperty("mark", requestCar.getMark());
        jsonObject.addProperty("model", requestCar.getModel());
        jsonObject.addProperty("price", requestCar.getPrice());

        return jsonObject.toString();
    }

    /**
     * Метод принимает в себя длину уникальной строки, которую добавит в Марку/Модель и сгенерирует остальные данные
     */
    public static Car createRandomCar(int length) {
        Random r = new Random();
        char[] charArray = new char[length];
        for (int i = 0; i < length; i++) {
            char c = (char) (r.nextInt(25) + 65);
            charArray[i] = c;
        }
        String randomString = new String(charArray);
        DecimalFormat df = new DecimalFormat("#.##");
        Float price = (float) (Math.round(r.nextFloat() * 1000000) / 100.0);
        DAO engineTypeDao = new DAO<>(EngineType.class);
        long randomETypeId = r.nextInt(engineTypeDao.getAllSize().intValue() + 1);
        EngineType engineType = (EngineType) engineTypeDao.getByID(randomETypeId);
        Car randomCar = new Car(engineType.getType_name(), "mark" + randomString, "model" + randomString, price);
        return randomCar;
    }
}
