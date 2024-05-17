package dao;

import entities.House;
import entities.ParkingPlace;
import entities.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HouseDAO {
    static Configuration configuration = new Configuration();

    public static House getByID(long id) {
        configuration.addAnnotatedClass(House.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        try (SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
             Session session = sessionFactory.openSession()) {
            House house = (House) session.load(House.class, id);
            List <Person> lodgers = PersonDAO.getByHouseId(house.getId());
            house.setLodgers(lodgers);
            List <ParkingPlace> parkingPlaces = ParkingPlaceDAO.getByHouseId(house.getId());
            house.setParkingPlaces(parkingPlaces);
            System.out.println(house.toString());
            return house;
        }
    }

    public static List<House> getAll() {
        configuration.addAnnotatedClass(House.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        try (SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
             Session session = sessionFactory.openSession()) {
            List<House> houses = session.createQuery("from House").list();
            return houses;
        }
    }
}


