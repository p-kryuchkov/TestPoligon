package api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.hc.client5.http.auth.AuthScope;
import org.apache.hc.client5.http.auth.UsernamePasswordCredentials;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.auth.BasicCredentialsProvider;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.message.BasicHeader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Base64;
import java.util.Properties;

import static api.Specifications.*;


public class Login {
    private static final String username = Specifications.getProperties().getProperty("apiLogin");
    private static final String password = Specifications.getProperties().getProperty("apiPassword");

    public static String getToken() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(baseUrl + login);
        httpPost.addHeader("Content-Type", "application/json");
        String request = "{\"username\": \""+ username + "\", \"password\": \""+ password + "\"}";
        StringEntity entity = new StringEntity(request, ContentType.APPLICATION_JSON);
        httpPost.setEntity(entity);
        System.out.println(httpPost + request);

        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            System.out.println(response.toString());
            String responseBody = EntityUtils.toString(response.getEntity());
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);
            String accessToken = jsonObject.get("access_token").getAsString();
            System.out.println(accessToken);
            return accessToken;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
