package dao;

import entities.Person;
import org.hibernate.Session;

import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public class PersonDAO extends DAO {
    public PersonDAO() {
        super(Person.class);
    }

    public Person getPersonIdWithoutHouse() {
        try (Session session = sessionFactory.openSession()) {
            List<Person> persons = session.createQuery("from Person where houseId is null").list();
            if (!persons.isEmpty()) {
                Person person = persons.get(0);
                System.out.println(person.toString());
                return person;
            } else {
                fail("Бездомных в базе нет");
                return null;
            }
        }
    }

}
