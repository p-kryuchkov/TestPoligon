package api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

import static api.Login.getToken;

public class ApiMethods {

    private final OkHttpClient client;

    public ApiMethods() {
        this.client = new OkHttpClient();
    }

    public String executeHttpRequest(Request request) throws IOException {
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public Request getRequest(String endpoint) {
        return new Request.Builder()
                .url(endpoint)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + getToken())
                .build();
    }

    public Request postRequest(String endpoint, String request) {
        RequestBody body = RequestBody.create(request, okhttp3.MediaType.parse("application/json"));
        return new Request.Builder()
                .url(endpoint)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "*/*")
                .addHeader("Authorization", "Bearer " + getToken())
                .build();
    }
}
