package api;

import com.google.gson.Gson;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;

import static api.Login.getToken;
import static org.junit.jupiter.api.Assertions.fail;

import api.Specifications.*;

public class ApiMethods<T> {

    static Gson gson = new Gson();

    public ApiMethods() {
    }

    public String executeHttpRequest(HttpUriRequestBase httpRequest) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(httpRequest)) {
            String responseBody = EntityUtils.toString(response.getEntity());
            if (responseBody == null) {
                fail("Ответ не получен");
            }
            return responseBody;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public HttpGet getRequest(String endpoint) {
        HttpGet httpGet = new HttpGet(endpoint);
        Specifications.setRequestSpec(httpGet);
        System.out.println(httpGet);
        return httpGet;
    }

    public HttpPost postRequest(String endpoint, String request) {
        HttpPost httpPost = new HttpPost(endpoint);
        Specifications.setRequestSpec(httpPost);
        StringEntity entity = new StringEntity(request, ContentType.APPLICATION_JSON);
        httpPost.setEntity(entity);
        System.out.println(httpPost + request);
        return httpPost;
    }
}
