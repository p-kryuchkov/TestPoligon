package testsAPI;

import api.PersonMethods;
import dao.CarDAO;
import dao.PersonDAO;
import entities.Car;
import entities.Person;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PersonTest {
    PersonDAO daoPerson = new PersonDAO();
    CarDAO daoCar = new CarDAO();

    @Test
    @DisplayName("Проверка создания юзера")
    public void testCreatePerson() {
        Long sizeBefore = daoPerson.getAllSize();
        Person requestPerson = PersonMethods.createRandomPerson(12);
        Person responsePerson = PersonMethods.createPerson(requestPerson);
        Person daoPersonResult = (Person) daoPerson.getValueByFieldName("firstName", responsePerson.getFirstName());
        Long sizeAfter = daoPerson.getAllSize();
        assertTrue(requestPerson.equalsWithotId(responsePerson), "Отправленные и полученные данные не совпадают");
        assertTrue(requestPerson.equalsWithotId(daoPersonResult), "Отправленные данные и данные в БД не совпадают");
        assertEquals(sizeAfter, sizeBefore + 1, "Количество юзеров не изменилось");
    }

    @Test
    @DisplayName("Проверка покупки машины")
    public void testBuyCar() {
        Car requestCar = CarDAO.getCarIdWithoutPerson();
        Assumptions.assumeTrue(!requestCar.equals(null), "В базе нет свободных машин");
        Person requestPerson = new Person();
        for (Object o : daoPerson.getAll()) {
            Person person = (Person) o;
            if (person.getMoney() > requestCar.getPrice()) {
                requestPerson = person;
                break;
            }
            else continue;
        }
        ;
        Person responsePerson = PersonMethods.buyCar(requestPerson.getId(), requestCar.getId());
        Car daoCarResult = (Car) daoCar.getByID(requestCar.getId());
        assertEquals(responsePerson.getId(), requestPerson.getId(), "id покупателя в запросе и ответе не совпадает");
        assertEquals(responsePerson.getMoney(), requestPerson.getMoney() - daoCarResult.getPrice(), "У покупателя некорректно списались деньги за покупку");
        assertEquals(requestPerson.getId(), daoCarResult.getPerson().getId(), "В БД некорректно присвоен ID владельца машины");
    }
}
