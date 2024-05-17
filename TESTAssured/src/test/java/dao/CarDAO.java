package dao;

import entities.Car;
import entities.EngineType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class CarDAO {
    static Configuration configuration = new Configuration();

    public static Car getByID(long id) {
        configuration.addAnnotatedClass(Car.class);
        configuration.addAnnotatedClass(EngineType.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        try (SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
             Session session = sessionFactory.openSession()) {
            Car car = (Car) session.load(Car.class, id);
            EngineType engineType = (EngineType) session.load(EngineType.class, car.getEngineTypeId());
            car.setEngineType(engineType.getType_name());
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
}
