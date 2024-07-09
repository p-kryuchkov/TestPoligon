package api;


import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.fail;

public class Specifications {
    public static Properties getProperties() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("src/test/resources/project.properties"));
        } catch (IOException e) {
            fail("Файл project.properties не найден");
        }
        return properties;
    }

    static Properties properties = getProperties();
    public static final String baseUrl = properties.getProperty("baseURL");
    public static final String login = "/login";
    public static final String car = "/car";
    public static final String person = "/user";
    public static final String house = "/house";
}
