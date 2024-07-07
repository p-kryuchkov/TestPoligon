package testsAPI;


import api.CarMethods;
import dao.CarDAO;
import dao.DAO;
import entities.Car;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CarTest {
DAO daoCarDao = new DAO<>(Car.class);

    @Test
    @DisplayName("Проверка создания автомобиля")
    public void testCreateCar() {
        Long sizeBefore = daoCarDao.getAllSize();
        Car requestCar = CarMethods.createRandomCar(4);
        Car responceCar = CarMethods.createCar(requestCar);
        Long sizeAfter = daoCarDao.getAllSize();
        Car daoCar = (Car) daoCarDao.getByID(responceCar.getId());
        assertTrue(requestCar.equalsWithotId(responceCar), "Отправленные и полученные данные не совпадают");
        assertEquals(responceCar,daoCar,"Машины в ответе и в БД не совпадают");
        assertEquals(sizeAfter, sizeBefore + 1, "Количество машин не изменилось");
    }
}