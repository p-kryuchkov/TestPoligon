package testsAPI;

import api.CarMethods;
import api.HouseMethods;
import dao.CarDAO;
import dao.HouseDAO;
import dao.ParkingPlaceDAO;
import entities.Car;
import entities.House;
import entities.ParkingPlace;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HouseTest {

    @Test
    @DisplayName("Проверка создания дома и сопутствующих сущностей")
    public void createHouse() {
        int sizeBefore = HouseDAO.getAll().size();
        int ppSizeBefore = ParkingPlaceDAO.getAll().size();
        List<ParkingPlace> parkingPlaces = new ArrayList<>();
        parkingPlaces.add(new ParkingPlace(true,false,6));
        parkingPlaces.add(new ParkingPlace(false, true,3));
        parkingPlaces.add(new ParkingPlace(true,false,1));
        House requestHouse = new House(4,329867F,parkingPlaces);
        House responceHouse = HouseMethods.createHouse(requestHouse);
        System.out.println(requestHouse.toString() + responceHouse.toString());
        assertTrue(requestHouse.equalsWithoutId(responceHouse), "Отправленные и полученные данные не совпадают");
        assertTrue(requestHouse.equalsParkingPlaces(responceHouse.getParkingPlaces()), "Отправленные и полученные парковочные места не совпадают");
        House daoHouse = (House) Hibernate.unproxy(HouseDAO.getByID(responceHouse.getId()));
        assertTrue(requestHouse.equalsWithoutId(daoHouse), "Отправленные данные и данные в БД не совпадают");
        List<ParkingPlace> parkingPlaceList =(List<ParkingPlace>) Hibernate.unproxy(ParkingPlaceDAO.getByHouseId(responceHouse.getId()));
        assertTrue(requestHouse.equalsParkingPlaces(parkingPlaceList),"Отправленные парковочные места не совпадают с БД");
        int sizeAfter = HouseDAO.getAll().size();
        int ppSizeAfter = ParkingPlaceDAO.getAll().size();
        assertEquals(sizeAfter, sizeBefore + 1, "Количество домов не изменилось");
        assertEquals(ppSizeAfter, ppSizeBefore + parkingPlaces.size());
        assertTrue(requestHouse.equalsWithoutId(HouseMethods.getHouseById(responceHouse.getId())),"При поиске по id находится другой дом");
    }
}
