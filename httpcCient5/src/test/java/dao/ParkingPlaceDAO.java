package dao;

import entities.ParkingPlace;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ParkingPlaceDAO {
    static Configuration configuration = new Configuration();

    public static ParkingPlace getByID(long id) {
        configuration.addAnnotatedClass(ParkingPlace.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        try (SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
             Session session = sessionFactory.openSession()) {
            ParkingPlace parkingPlace = (ParkingPlace) session.load(ParkingPlace.class, id);
            System.out.println(parkingPlace.toString());
            return parkingPlace;
        }
    }

    public static List<ParkingPlace> getAll() {
        configuration.addAnnotatedClass(ParkingPlace.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        try (SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
             Session session = sessionFactory.openSession()) {
            List<ParkingPlace> parkingPlaces = session.createQuery("from ParkingPlace").list();
            return parkingPlaces;
        }
    }

    public static List<ParkingPlace> getByHouseId(Long houseId) {
        configuration.addAnnotatedClass(ParkingPlace.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        try (SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
             Session session = sessionFactory.openSession()) {
            List<ParkingPlace> parkingPlaces = session.createQuery("from ParkingPlace where houseId = :param").setParameter("param", houseId).list();
            return parkingPlaces;
        }
    }
    public static Long getAllParkingPlacesSize() {
        configuration.addAnnotatedClass(ParkingPlace.class);
        StandardServiceRegistryBuilder builder
                = new StandardServiceRegistryBuilder().applySettings((configuration.getProperties()));
        try (SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
             Session session = sessionFactory.openSession()) {
            Long size = session.createQuery("from ParkingPlace").getResultCount();
            return size;
        }
    }
}
