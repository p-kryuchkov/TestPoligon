package testsAPI;

import api.PersonMethods;
import dao.CarDAO;
import dao.PersonDAO;
import entities.Car;
import entities.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    PersonDAO daoPerson = new PersonDAO();
    CarDAO daoCar = new CarDAO();
    @Test
    @DisplayName("Проверка создания юзера")
    public void testCreatePerson() {
        Long sizeBefore = daoPerson.getAllSize();
        Person requestPerson = PersonMethods.createRandomPerson(12);
        Person responsePerson = PersonMethods.createPerson(requestPerson);
        Person daoPersonResult = (Person) daoPerson.getByID(responsePerson.getId());
        Long sizeAfter = daoPerson.getAllSize();
        assertTrue(requestPerson.equalsWithotId(responsePerson), "Отправленные и полученные данные не совпадают");
        assertEquals(responsePerson, daoPersonResult, "Отправленные данные и данные в БД не совпадают");
        assertEquals(sizeAfter, sizeBefore + 1, "Количество юзеров не изменилось");
    }
    @Test
    @DisplayName("Проверка покупки машины")
    public void testBuyCar(){
        try {
            Person requestPerson = (Person) daoPerson.getByID(17);
            Long carId = CarDAO.getCarIdWithoutPerson().getId();
            Person responsePerson = PersonMethods.buyCar(requestPerson.getId(), carId);
            Car daoCarResult = (Car) daoCar.getByID(carId);
            assertEquals(responsePerson.getId(), requestPerson.getId(), "id покупателя в запросе и ответе не совпадает");
            assertEquals(responsePerson.getMoney(), requestPerson.getMoney() - daoCarResult.getPrice(), "У покупателя некорректно списались деньги за покупку");
            assertEquals(requestPerson.getId(), daoCarResult.getPerson().getId(), "В БД некорректно присвоен ID владельца машины");
        }catch (NullPointerException e){
            fail("В базе нет свободных машин для покупки");
        }
    }
}
