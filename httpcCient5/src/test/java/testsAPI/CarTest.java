package testsAPI;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static api.CarMethods.getCarById;
import static api.Login.getToken;

public class CarTest {
    @Test
    @DisplayName("Тестлогин")
    public void testLogin(){
        getToken();
    }
    @Test
    @DisplayName("Car get")
    public void testCar(){getCarById(2);}
}
