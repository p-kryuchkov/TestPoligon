package dao;

import entities.Car;
import entities.EngineType;
import entities.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public class PersonDAO extends DAO {
    static SessionFactory sessionFactory;

    static {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Person.class);
        configuration.addPackage("entities");
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(builder.build());
    }

    public PersonDAO(Class type) {
        super(Person.class);
    }

    public static Person getPersonIdWithoutHouse() {
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
