package testsAPI;


import api.CarMethods;
import dao.CarDAO;
import entities.Car;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CarTest {
    @Test
    public void testEntities(){
        Car carDAO = CarDAO.getByID(2);
Car car = CarMethods.getCarById(2);
assertEquals(car,carDAO);


    }

//    @Test
//    @DisplayName("Проверка создания автомобиля")
//    public void testCreateCar() {
//        int sizeBefore = CarDAO.getAll().size();
//        Car requestCar = new Car("Electric", "Moskvitch", "Noviy", 212.3F);
//        Car responceCar = CarMethods.createCar(requestCar);
//        assertTrue(requestCar.equalsWithotId(responceCar), "Отправленные и полученные данные не совпадают");
//        Car daoCar = (Car) Hibernate.unproxy(CarDAO.getByID(responceCar.getId()));
//        assertTrue(requestCar.equalsWithotId(daoCar), "Отправленные данные и данные в БД не совпадают");
//        int sizeAfter = CarDAO.getAll().size();
//        assertEquals(sizeAfter, sizeBefore + 1, "Количество машин не изменилось");
//        assertTrue(requestCar.equalsWithotId(CarMethods.getCarById(responceCar.getId())),"При поиске по id находится другая машина");
//    }
}