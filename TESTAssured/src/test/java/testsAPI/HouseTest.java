package testsAPI;

import api.HouseMethods;
import dao.HouseDAO;
import dao.ParkingPlaceDAO;
import dao.PersonDAO;
import entities.House;
import entities.ParkingPlace;
import entities.Person;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static api.HouseMethods.createRandomHouse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HouseTest {
    HouseDAO daoHouse = new HouseDAO();
    ParkingPlaceDAO daoParkingPlace = new ParkingPlaceDAO();
    PersonDAO daoPerson = new PersonDAO();

    @Test
    @DisplayName("Проверка создания дома и сопутствующих сущностей")
    public void testCreateHouse() {
        Long sizeHouseBefore = daoHouse.getAllSize();
        Long sizeParkingPlaceBefore = daoParkingPlace.getAllSize();
        List<ParkingPlace> parkingPlaces = new ArrayList<>();
        parkingPlaces.add(new ParkingPlace(true, false, 6));
        parkingPlaces.add(new ParkingPlace(false, true, 3));
        parkingPlaces.add(new ParkingPlace(true, false, 1));
        House requestHouse = createRandomHouse();
        requestHouse.setParkingPlaces(parkingPlaces);
        House responseHouse = HouseMethods.createHouse(requestHouse);
        House daoHouseResult = (House) daoHouse.getByID(responseHouse.getId()); // Да я возьму ид из апи, но потом все равно сравню уникальные данные типа price и сверю
        // House daoHouseResult = (House) daoHouse.getValueByFieldName("price", requestHouse.getPrice()); Очень бы хотелось сделать так, но гибернейт коряво обрабатывает Float и BigDecimal, не смог победить(((
        List<ParkingPlace> parkingPlaceList = daoHouseResult.getParkingPlaces();
        Long sizeHouseAfter = daoHouse.getAllSize();
        Long sizeParkingPlaceAfter = daoParkingPlace.getAllSize();
        assertTrue(requestHouse.equalsWithoutId(responseHouse), "Отправленные и полученные данные не совпадают");
        assertTrue(requestHouse.equalsWithoutId(daoHouseResult), "Дома в запросе и в БД не совпадают");
        assertTrue(requestHouse.equalsParkingPlaces(responseHouse.getParkingPlaces()), "В запросе и в ответе парковочные места не совпадают");
        assertTrue(requestHouse.equalsParkingPlaces(parkingPlaceList), "В запросе и в БД парковочные места не совпадают");
        assertEquals(sizeHouseAfter, sizeHouseBefore + 1, "Количество домов не изменилось");
        assertEquals(sizeParkingPlaceAfter, sizeParkingPlaceBefore + parkingPlaces.size(), "Количество парковочных мест не изменилось");
    }

    @Test
    @DisplayName("Проверка заселения в дом")
    public void testSettle() {
        List<House> houses = daoHouse.getAll();
        House requestHouse = houses.get(new Random().nextInt(houses.size()));
        Integer lodgersSizeBefore = requestHouse.getLodgers().size();
        Person requestPerson = daoPerson.getPersonIdWithoutHouse();
        Assumptions.assumeTrue(!requestPerson.equals(null), "В базе нет бездомных");
        House responseHouse = HouseMethods.settle(requestHouse.getId(), requestPerson.getId());
        Person daoPersonResult = (Person) daoPerson.getByID(requestPerson.getId());
        assertEquals(responseHouse.getId(), requestHouse.getId(), "id дома в запросе и ответе не совпадает");
        assertEquals(responseHouse.getLodgers().size(), lodgersSizeBefore + 1, "количество жильцов после заселения некорректно");
        assertTrue(responseHouse.getLodgersIds().contains(requestPerson.getId()), "в списке жильцов нет жильца из запроса");
        assertEquals(requestPerson.getMoney() - responseHouse.getPrice(), daoPersonResult.getMoney(), "Количество денег у жильца не изменилось");
    }
}
