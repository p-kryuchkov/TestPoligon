package dao;

import entities.Car;
import entities.ParkingPlace;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ParkingPlaceDAO extends DAO{
    Configuration configuration = new Configuration();
    @Override
    public ParkingPlace getByID(long id) {
        configuration.addAnnotatedClass(ParkingPlace.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        try (SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
             Session session = sessionFactory.openSession()) {
            ParkingPlace parkingPlace = (ParkingPlace) session.load(ParkingPlace.class, id);
            System.out.println(parkingPlace.toString());
            return parkingPlace;
        }
    }
    @Override
    public List<ParkingPlace> getAll() {
        configuration.addAnnotatedClass(ParkingPlace.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        try (SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
             Session session = sessionFactory.openSession()) {
            List<ParkingPlace> parkingPlaces = session.createQuery("from ParkingPlace").list();
            return parkingPlaces;
        }
    }
}
