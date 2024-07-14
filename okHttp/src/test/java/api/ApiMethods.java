package api;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

import static api.Login.getToken;
import static api.Specifications.setRequestSpec;

public class ApiMethods {
    static Gson gson = new Gson();
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
        return setRequestSpec( new Request.Builder()
                .url(endpoint))
                .build();
    }

    public Request postRequest(String endpoint, String request) {
        RequestBody body = RequestBody.create(request, okhttp3.MediaType.parse("application/json"));
        return setRequestSpec(new Request.Builder().url(endpoint).post(body))
                .build();
    }
}
