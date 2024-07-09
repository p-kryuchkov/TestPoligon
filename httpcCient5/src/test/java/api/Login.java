package api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import static api.Specifications.baseUrl;
import static api.Specifications.login;
import static org.junit.jupiter.api.Assertions.fail;


public class Login {
    private static final String username = Specifications.getProperties().getProperty("apiLogin");
    private static final String password = Specifications.getProperties().getProperty("apiPassword");

    public static String getToken() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(baseUrl + login);
        httpPost.addHeader("Content-Type", "application/json");
        String request = "{\"username\": \"" + username + "\", \"password\": \"" + password + "\"}";
        StringEntity entity = new StringEntity(request, ContentType.APPLICATION_JSON);
        httpPost.setEntity(entity);
        System.out.println(httpPost + request);

        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            System.out.println(response.toString());
            String responseBody = EntityUtils.toString(response.getEntity());
            if (responseBody == null) fail("Ответ при логине не получен");
            Gson gson = new Gson();
            try {
                JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);
                if (jsonObject.get("access_token") == null) fail("Авторизационный токен не получен");
                String accessToken = jsonObject.get("access_token").getAsString();
                System.out.println(accessToken);
                return accessToken;
            } catch (NullPointerException e) {
                fail("Ответ при логине пустой");
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
