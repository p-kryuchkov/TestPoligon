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

public class CarDAO {
    static Configuration configuration = new Configuration();

    public static Car getByID(long id) {
        configuration.addAnnotatedClass(Car.class);
        configuration.addAnnotatedClass(EngineType.class);
        configuration.addAnnotatedClass(Person.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        try (SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
             Session session = sessionFactory.openSession()) {
            Car car = (Car) session.get(Car.class, id);
          System.out.println(car.toString());
            return car;
        }
    }

    public static List<Car> getAll() {
        configuration.addAnnotatedClass(Car.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        try (SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
             Session session = sessionFactory.openSession()) {
            List<Car> cars = session.createQuery("from Car").list();
            return cars;
        }
    }

    public static Car getCarIdWithoutPerson() {
        configuration.addAnnotatedClass(Car.class);
        configuration.addAnnotatedClass(EngineType.class);
        configuration.addAnnotatedClass(Person.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings((configuration.getProperties()));
        try (SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
             Session session = sessionFactory.openSession()) {
            List<Car> cars = session.createQuery("from Car where person is null").list();
            if (!cars.isEmpty()) {
                Car car = cars.get(0);
                System.out.println(car.toString());
                return car;
            } else {
                fail("Свободных машин в базе нет");
                return null;
            }
        }
    }

    public static Long getAllCarsSize() {
        configuration.addAnnotatedClass(Car.class);
        configuration.addAnnotatedClass(EngineType.class);
        configuration.addAnnotatedClass(Person.class);
        StandardServiceRegistryBuilder builder
                = new StandardServiceRegistryBuilder().applySettings((configuration.getProperties()));
        try (SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
             Session session = sessionFactory.openSession()) {
            Long size = session.createQuery("from Car").getResultCount();
            return size;
        }
    }
}
