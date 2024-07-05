package testsAPI;


import api.CarMethods;
import dao.CarDAO;
import entities.Car;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CarTest {


    @Test
    @DisplayName("Проверка создания автомобиля")
    public void testCreateCar() {
        Long sizeBefore = CarDAO.getAllCarsSize();
        Car requestCar = new Car("Electric", "Moskvitch", "Noviy", 212.3F);
        Car responceCar = CarMethods.createCar(requestCar);
        Long sizeAfter = CarDAO.getAllCarsSize();
        Car daoCar = CarDAO.getByID(responceCar.getId());
        assertTrue(requestCar.equalsWithotId(responceCar), "Отправленные и полученные данные не совпадают");
        assertEquals(responceCar,daoCar,"Машины в ответе и в БД не совпадают");
        assertEquals(sizeAfter, sizeBefore + 1, "Количество машин не изменилось");
    }
}