package testsAPI;

import api.CarMethods;
import api.PersonMethods;
import dao.CarDAO;
import dao.PersonDAO;
import entities.Car;
import entities.Person;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    @Test
    @DisplayName("Проверка создания юзера")
    public void testCreatePerson() {
        int sizeBefore = PersonDAO.getAll().size();
        Person requestPerson = new Person("Ivan","Petrov",22,"MALE",14264F);
        Person responcePerson = PersonMethods.createPerson(requestPerson);
        assertTrue(requestPerson.equalsWithotId(responcePerson), "Отправленные и полученные данные не совпадают");
        Person daoPerson = (Person) Hibernate.unproxy(PersonDAO.getByID(responcePerson.getId()));
      //  System.out.println(daoCar.getEngineType() + requestCar.toString() + responceCar.toString() + daoCar.toString());
        assertTrue(requestPerson.equalsWithotId(daoPerson), "Отправленные данные и данные в БД не совпадают");
        int sizeAfter = PersonDAO.getAll().size();
        assertEquals(sizeAfter, sizeBefore + 1, "Количество юзеров не изменилось");
        assertTrue(requestPerson.equalsWithotId(PersonMethods.getPersonByID(responcePerson.getId())),"При поиске по id находится другой юзер");
    }
    @Test
    @DisplayName("Проверка покупки машины")
    public void testBuyCar(){
        Person requestPerson = PersonDAO.getByID(17);
        Long carId = CarDAO.getCarIdWithoutPerson().getId();
        if (carId.equals(null)) fail("Свободных машин в базе нет");
        else {
            Person responsePerson = PersonMethods.buyCar(requestPerson.getId(), carId);
            assertTrue(responsePerson.getId().equals(requestPerson.getId()), "id покупателя в запросе и ответе не совпадает");
            assertTrue(responsePerson.getMoney().equals(requestPerson.getMoney() - CarDAO.getByID(carId).getPrice()), "У покупателя некорректно списались деньги за покупку");
            assertTrue(requestPerson.getId().equals(CarDAO.getByID(carId).getPerson()), "В БД некорректно присвоен ID владельца машины");
        }
    }
}
