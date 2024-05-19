package dao;

import entities.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public class PersonDAO {
    static Configuration configuration = new Configuration();

    public static Person getByID(long id) {
        configuration.addAnnotatedClass(Person.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        try (SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
             Session session = sessionFactory.openSession()) {
            Person person = (Person) session.load(Person.class, id);
            if (person.isMale()) person.setSex("MALE");
            return person;
        }
    }

    public static List<Person> getAll() {
        configuration.addAnnotatedClass(Person.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        try (SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
             Session session = sessionFactory.openSession()) {
            List<Person> personList = session.createQuery("from Person").list();
            return personList;
        }
    }

    public static List<Person> getByHouseId(Long houseId) {
        configuration.addAnnotatedClass(Person.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        try (SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
             Session session = sessionFactory.openSession()) {
            List<Person> personList = session.createQuery("from Person where houseId = :param").setParameter("param", houseId).list();
            return personList;
        }
    }

    public static Person getPersonIdWithoutHouse() {
        configuration.addAnnotatedClass(Person.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings((configuration.getProperties()));
        try (SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
             Session session = sessionFactory.openSession()) {
            List<Person> persons = session.createQuery("from Person where houseId is null").list();
            if (!persons.isEmpty()) {
                Person person = persons.get(0);
                System.out.println(person.toString());
                return person;
            } else {
                return null;
            }
        }
    }
}
