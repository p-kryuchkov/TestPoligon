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

public class CarDAO extends DAO {
    static SessionFactory sessionFactory;

    static {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Car.class);
        configuration.addPackage("entities");
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(builder.build());
    }
    public CarDAO(Class type) {
        super(Car.class);
    }





    public static Car getCarIdWithoutPerson() {
        try (Session session = sessionFactory.openSession()) {
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
}
