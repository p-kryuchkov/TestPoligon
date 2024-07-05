package api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.EngineTypeDAO;
import entities.Car;
import entities.EngineType;
import entities.Person;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import static api.Login.getToken;
import static api.Specifications.*;

import static org.junit.jupiter.api.Assertions.fail;


public class PersonMethods {
    public static Person getPersonByID(long id) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(baseUrl + person + "/" + id);
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
            } catch (NullPointerException e) {
                fail("Ответ пустой");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public static Person createPerson(Person requestPerson) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(baseUrl + person);
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.addHeader("Accept", "*/*");
        httpPost.addHeader("Authorization", "Bearer " + getToken());
        String request = "{\"firstName\": \"" + requestPerson.getFirstName() + "\", " +
                "\"secondName\": \"" + requestPerson.getSecondName() + "\", " +
                "\"age\": " + requestPerson.getAge() + ", " +
                "\"sex\": \"" + requestPerson.getSex() + "\", " +
                "\"money\": " + requestPerson.getMoney() +
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
            } catch (NullPointerException e) {
                fail("Ответ пустой");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public static Person buyCar(long personId, long carId) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(baseUrl + person + "/" + personId + "/buyCar/" + carId);
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
                String firstName = jsonObject.get("firstName").getAsString();
                String secondName = jsonObject.get("secondName").getAsString();
                int age = jsonObject.get("age").getAsInt();
                String sex = jsonObject.get("sex").getAsString();
                Long personIdJson = jsonObject.get("id").getAsLong();
                Float money = jsonObject.get("money").getAsFloat();
                Person responsePerson = new Person(firstName, secondName, age, sex, money);
                if (sex.equals("MALE")) responsePerson.setMale(true);
                else responsePerson.setMale(false);
                responsePerson.setId(personIdJson);
                System.out.println(responsePerson.toString());
                return responsePerson;
            } catch (NullPointerException e) {
                fail("Ответ пустой");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}

