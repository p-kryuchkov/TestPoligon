package dao;

import entities.ParkingPlace;
import org.hibernate.Session;

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
