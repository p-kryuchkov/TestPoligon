package testsAPI;

import api.PersonMethods;
import dao.CarDAO;
import dao.PersonDAO;
import entities.Car;
import entities.Person;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static api.PersonMethods.parsePersonToJson;
import static api.Specifications.baseUrl;
import static api.Specifications.person;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PersonTest {
    PersonDAO daoPerson = new PersonDAO();
    CarDAO daoCar = new CarDAO();
    PersonMethods personApi = new PersonMethods();

    @Test
    @DisplayName("Проверка создания юзера")
    public void testCreatePerson() throws IOException {
        Long sizeBefore = daoPerson.getAllSize();
        Person requestPerson = PersonMethods.createRandomPerson(12);
        String json = personApi.executeHttpRequest(personApi.postRequest(baseUrl + person, parsePersonToJson(requestPerson)));
        Person responsePerson = PersonMethods.parseJsonToPerson(json);
        Person daoPersonResult = (Person) daoPerson.getValueByFieldName("firstName", responsePerson.getFirstName());
        Long sizeAfter = daoPerson.getAllSize();
        assertTrue(requestPerson.equalsWithotId(responsePerson), "Отправленные и полученные данные не совпадают");
        assertTrue(requestPerson.equalsWithotId(daoPersonResult), "Отправленные данные и данные в БД не совпадают");
        assertEquals(sizeAfter, sizeBefore + 1, "Количество юзеров не изменилось");
    }

    @Test
    @DisplayName("Проверка покупки машины")
    public void testBuyCar() throws IOException {
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
        String json = personApi.executeHttpRequest(personApi.postRequest(baseUrl + person + "/" + requestPerson.getId() + "/buyCar/" + requestCar.getId(), parsePersonToJson(requestPerson)));
        Person responsePerson = PersonMethods.parseJsonToPerson(json);        Car daoCarResult = (Car) daoCar.getByID(requestCar.getId());
        assertEquals(responsePerson.getId(), requestPerson.getId(), "id покупателя в запросе и ответе не совпадает");
        assertEquals(responsePerson.getMoney(), requestPerson.getMoney() - daoCarResult.getPrice(), "У покупателя некорректно списались деньги за покупку");
        assertEquals(requestPerson.getId(), daoCarResult.getPerson().getId(), "В БД некорректно присвоен ID владельца машины");
    }
}
