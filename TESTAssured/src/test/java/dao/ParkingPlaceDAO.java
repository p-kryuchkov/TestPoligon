package dao;

import entities.Car;
import entities.House;
import entities.ParkingPlace;
import entities.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ParkingPlaceDAO extends DAO {
       public ParkingPlaceDAO() {
        super(ParkingPlace.class);
    }


    public static List<ParkingPlace> getByHouseId(Long houseId) {
        try (Session session = sessionFactory.openSession()) {
            List<ParkingPlace> parkingPlaces = session.createQuery("from ParkingPlace where houseId = :param").setParameter("param", houseId).list();
            return parkingPlaces;
        }
    }
}
