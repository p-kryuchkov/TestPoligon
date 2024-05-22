package testsAPI;

import api.PersonMethods;
import dao.CarDAO;
import dao.PersonDAO;
import entities.Person;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    @Test
    @DisplayName("Проверка создания юзера")
    public void testCreatePerson() {
        Long sizeBefore = PersonDAO.getAllPersonSize();
        Person requestPerson = new Person("Ivan","Petrov",22,"MALE",14264F);
        Person responsePerson = PersonMethods.createPerson(requestPerson);
        Person daoPerson = PersonDAO.getByID(responsePerson.getId());
        Long sizeAfter = PersonDAO.getAllPersonSize();
        assertTrue(requestPerson.equalsWithotId(responsePerson), "Отправленные и полученные данные не совпадают");
        assertEquals(responsePerson, daoPerson, "Отправленные данные и данные в БД не совпадают");
        assertEquals(sizeAfter, sizeBefore + 1, "Количество юзеров не изменилось");
    }
    @Test
    @DisplayName("Проверка покупки машины")
    public void testBuyCar(){
        try {
            Person requestPerson = PersonDAO.getByID(17);
            Long carId = CarDAO.getCarIdWithoutPerson().getId();
            Person responsePerson = PersonMethods.buyCar(requestPerson.getId(), carId);
            assertEquals(responsePerson.getId(), requestPerson.getId(), "id покупателя в запросе и ответе не совпадает");
            assertEquals(responsePerson.getMoney(), requestPerson.getMoney() - CarDAO.getByID(carId).getPrice(), "У покупателя некорректно списались деньги за покупку");
            assertEquals(requestPerson.getId(), CarDAO.getByID(carId).getPerson(), "В БД некорректно присвоен ID владельца машины");
        }catch (NullPointerException e){
            fail("В базе нет свободных машин для покупки");
        }
    }
}
