package testsAPI;


import api.CarMethods;
import dao.CarDAO;
import entities.Car;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CarTest {
    CarDAO daoCar = new CarDAO();

    @Test
    @DisplayName("Проверка создания автомобиля")
    public void testCreateCar() {
        Long sizeBefore = daoCar.getAllSize();
        Car requestCar = CarMethods.createRandomCar(4);
        Car responceCar = CarMethods.createCar(requestCar);
        Long sizeAfter = daoCar.getAllSize();
        Car daoCarResult = (Car) daoCar.getValueByFieldName("mark",requestCar.getMark());
        assertTrue(requestCar.equalsWithotId(responceCar), "Отправленные и полученные данные не совпадают");
        assertEquals(responceCar,daoCarResult,"Машины в ответе и в БД не совпадают");
        assertEquals(sizeAfter, sizeBefore + 1, "Количество машин не изменилось");
    }
}