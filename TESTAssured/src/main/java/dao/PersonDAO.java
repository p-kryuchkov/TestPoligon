package dao;

import entities.Car;
import entities.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class PersonDAO extends DAO{
    Configuration configuration = new Configuration();
    @Override
    public Person getByID(long id) {
        configuration.addAnnotatedClass(Person.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        try (SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
             Session session = sessionFactory.openSession()) {
            Person person = (Person) session.load(Person.class, id);
            System.out.println(person.toString());
            return person;
        }
    }
    @Override
    public List<Person> getAll() {
        configuration.addAnnotatedClass(Person.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        try (SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
             Session session = sessionFactory.openSession()) {
            List<Person> personList = session.createQuery("from Person").list();
            return personList;
        }
    }
}
