package testsAPI;

import api.PersonMethods;
import dao.CarDAO;
import dao.PersonDAO;
import dao.DAO;
import entities.Car;
import entities.Person;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    DAO daoPersonDao = new DAO<>(Person.class);
    DAO daoCarDao = new DAO<>(Car.class);
    @Test
    @DisplayName("Проверка создания юзера")
    public void testCreatePerson() {
        Long sizeBefore = daoPersonDao.getAllSize();
        Person requestPerson = PersonMethods.createRandomPerson(12);
        Person responsePerson = PersonMethods.createPerson(requestPerson);
        Person daoPerson = (Person) daoPersonDao.getByID(responsePerson.getId());
        Long sizeAfter =daoPersonDao.getAllSize();
        assertTrue(requestPerson.equalsWithotId(responsePerson), "Отправленные и полученные данные не совпадают");
        assertEquals(responsePerson, daoPerson, "Отправленные данные и данные в БД не совпадают");
        assertEquals(sizeAfter, sizeBefore + 1, "Количество юзеров не изменилось");
    }
    @Test
    @DisplayName("Проверка покупки машины")
    public void testBuyCar(){
        try {
            Person requestPerson = (Person) daoPersonDao.getByID(17);
            Long carId = CarDAO.getCarIdWithoutPerson().getId();
            Person responsePerson = PersonMethods.buyCar(requestPerson.getId(), carId);
            Car daoCar = (Car) daoCarDao.getByID(carId);
            assertEquals(responsePerson.getId(), requestPerson.getId(), "id покупателя в запросе и ответе не совпадает");
            assertEquals(responsePerson.getMoney(), requestPerson.getMoney() - daoCar.getPrice(), "У покупателя некорректно списались деньги за покупку");
            assertEquals(requestPerson.getId(), daoCar.getPerson().getId(), "В БД некорректно присвоен ID владельца машины");
        }catch (NullPointerException e){
            fail("В базе нет свободных машин для покупки");
        }
    }
}
