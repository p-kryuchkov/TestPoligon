package dao;

import entities.Car;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class CarDao extends DAO {
    Configuration configuration = new Configuration();
    @Override
    public Car getByID(long id) {
        configuration.addAnnotatedClass(Car.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        try (SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
             Session session = sessionFactory.openSession()) {
            Car car = (Car) session.load(Car.class, id);
            System.out.println(car.toString());
            return car;
        }
    }
    @Override
    public List<Car> getAll() {
        configuration.addAnnotatedClass(Car.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        try (SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
             Session session = sessionFactory.openSession()) {
            List<Car> cars = session.createQuery("from Car").list();
            return cars;
        }
    }
}
