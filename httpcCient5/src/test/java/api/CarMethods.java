package api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.DAO;
import dao.EngineTypeDAO;
import entities.Car;
import entities.EngineType;

import java.text.DecimalFormat;
import java.util.Random;

import static java.lang.Math.round;

public class CarMethods extends ApiMethods {
    public CarMethods() {
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
        String request = "{\"engineType\": \"" + requestCar.getEngineType().getType_name() + "\", " +
                "\"mark\": \"" + requestCar.getMark() + "\", " +
                "\"model\": \"" + requestCar.getModel() + "\", " +
                "\"price\": " + requestCar.getPrice() +
                "}";
        return request;
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
        Float price = (float) (round(r.nextFloat() * 1000000) / 100.0);
        DAO engineTypeDao = new DAO<>(EngineType.class);
        long randomETypeId = r.nextInt(engineTypeDao.getAllSize().intValue() + 1);
        EngineType engineType = (EngineType) engineTypeDao.getByID(randomETypeId);
        Car randomCar = new Car(engineType.getType_name(), "mark" + randomString, "model" + randomString, price);
        return randomCar;
    }
}

