package testsAPI;

import api.HouseMethods;
import dao.HouseDAO;
import dao.ParkingPlaceDAO;
import dao.PersonDAO;
import entities.House;
import entities.ParkingPlace;
import entities.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HouseTest {

    @Test
    @DisplayName("Проверка создания дома и сопутствующих сущностей")
    public void testCreateHouse() {
        Long sizeHouseBefore = HouseDAO.getAllHousesSize();
        Long sizeParkingPlaceBefore = ParkingPlaceDAO.getAllParkingPlacesSize();
        List<ParkingPlace> parkingPlaces = new ArrayList<>();
        parkingPlaces.add(new ParkingPlace(true, false, 6));
        parkingPlaces.add(new ParkingPlace(false, true, 3));
        parkingPlaces.add(new ParkingPlace(true, false, 1));
        House requestHouse = new House(4, 329867F, parkingPlaces);
        House responseHouse = HouseMethods.createHouse(requestHouse);
        House daoHouse = HouseDAO.getByID(responseHouse.getId());
        Long sizeHouseAfter = HouseDAO.getAllHousesSize();
        Long sizeParkingPlaceAfter = ParkingPlaceDAO.getAllParkingPlacesSize();
        assertTrue(requestHouse.equalsWithoutId(responseHouse), "Отправленные и полученные данные не совпадают");
        assertTrue(requestHouse.equalsWithoutId(daoHouse), "Дома в запросе и в БД не совпадают");
        assertTrue(requestHouse.equalsParkingPlaces(responseHouse.getParkingPlaces()), "В запросе и в отвеете парковочные места не совпадают");
        assertTrue(requestHouse.equalsParkingPlaces(daoHouse.getParkingPlaces()), "В запросе и в БД парковочные места не совпадают");
        assertEquals(sizeHouseAfter, sizeHouseBefore + 1, "Количество домов не изменилось");
        assertEquals(sizeParkingPlaceAfter, sizeParkingPlaceBefore + parkingPlaces.size(), "Количество парковочных мест не изменилось");
    }

    @Test
    @DisplayName("Проверка заселения в дом")
    public void testSettle() {
        House requestHouse = HouseDAO.getByID(12);
        Integer lodgersSizeBefore = requestHouse.getLodgers().size();
        try {
            Person requestPerson = PersonDAO.getPersonIdWithoutHouse();
            Long personId = requestPerson.getId();
            House responseHouse = HouseMethods.settle(requestHouse.getId(), requestPerson.getId());
            assertEquals(responseHouse.getId(), requestHouse.getId(), "id дома в запросе и ответе не совпадает");
            assertEquals(responseHouse.getLodgers().size(), lodgersSizeBefore + 1, "количество жильцов после заселения некорректно");
            assertTrue(responseHouse.getLodgersIds().contains(personId), "в списке жильцов нет жильца из запроса");
            assertEquals(requestPerson.getMoney() - responseHouse.getPrice(), PersonDAO.getByID(requestPerson.getId()).getMoney(), "Количество денег у жильца не изменилось");
        } catch (NullPointerException e) {
            fail("Нет свободных юзеров для заселения");
        }
    }

}
