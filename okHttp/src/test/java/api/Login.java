package api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

import static api.Specifications.baseUrl;
import static api.Specifications.login;

public class Login {
    private static final String username = Specifications.getProperties().getProperty("apiLogin");
    private static final String password = Specifications.getProperties().getProperty("apiPassword");

    public static String getToken() {
        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String json = "{\"username\": \"" + username + "\", \"password\": \"" + password + "\"}";
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(baseUrl + login)
                .addHeader("Content-Type", "application/json")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            String responseBody = response.body().string();
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);
            if (jsonObject.get("access_token") == null) {
                throw new IOException("Авторизационный токен не получен");
            }
            String accessToken = jsonObject.get("access_token").getAsString();
            System.out.println(accessToken);
            return accessToken;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
