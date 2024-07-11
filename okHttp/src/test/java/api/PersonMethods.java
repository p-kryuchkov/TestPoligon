package api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import entities.Person;
import okhttp3.*;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Random;

import static java.lang.Math.round;

public class PersonMethods extends ApiMethods {
    private final OkHttpClient client;

    public PersonMethods() {
        this.client = new OkHttpClient();
    }

    public static Person parseJsonToPerson(String response) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(response, JsonObject.class);
        String firstName = jsonObject.get("firstName").getAsString();
        String secondName = jsonObject.get("secondName").getAsString();
        int age = jsonObject.get("age").getAsInt();
        String sex = jsonObject.get("sex").getAsString();
        Long personId = jsonObject.get("id").getAsLong();
        Float money = jsonObject.get("money").getAsFloat();
        Person responsePerson = new Person(firstName, secondName, age, sex, money);
        if (sex.equals("MALE")) responsePerson.setMale(true);
        else responsePerson.setMale(false);
        responsePerson.setId(personId);
        System.out.println(responsePerson.toString());
        return responsePerson;
    }

    public static String parsePersonToJson(Person requestPerson) {
        return new Gson().toJson(requestPerson);
    }
    /**
     * Метод принимает в себя длину уникальной строки, которую добавит в Имя/Фамилию и сгенерирует остальные данные
     */
    public static Person createRandomPerson(int length) {
        Random r = new Random();
        char[] charArray = new char[length];
        for (int i = 0; i < length; i++) {
            char c = (char) (r.nextInt(25) + 65);
            charArray[i] = c;
        }
        String randomString = new String(charArray);
        DecimalFormat df = new DecimalFormat("#.##");
        Float money = (float) (round(r.nextFloat() * 1000000) / 100.0);
        Person randomPerson = new Person("firstName" + randomString, "secondName" + randomString, r.nextInt(99) + 1, r.nextBoolean(), money);
        return randomPerson;
    }
}
