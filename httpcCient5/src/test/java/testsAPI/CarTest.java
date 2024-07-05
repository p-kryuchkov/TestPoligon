package testsAPI;

import api.HouseMethods;
import dao.HouseDAO;
import dao.PersonDAO;
import entities.Car;
import entities.House;
import entities.ParkingPlace;
import entities.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static api.CarMethods.createCar;
import static api.CarMethods.getCarById;
import static api.HouseMethods.createHouse;
import static api.HouseMethods.getHouseById;
import static api.Login.getToken;

public class CarTest {
    @Test
    @DisplayName("Тестлогин")
    public void testLogin(){
        getToken();
    }
    @Test
    @DisplayName("Car get")
    public void testCar(){getCarById(2);
        Car requestCar = new Car("Electric", "Moskvitch", "Noviy", 212.3F);
    createCar(requestCar);}
    @Test
    @DisplayName("House")
    public void testHouse() {
        getHouseById(2);
        List<ParkingPlace> parkingPlaces = new ArrayList<>();
        parkingPlaces.add(new ParkingPlace(true, false, 6));
        parkingPlaces.add(new ParkingPlace(false, true, 3));
        parkingPlaces.add(new ParkingPlace(true, false, 1));
        House requestHouse = new House(4, 329867F, parkingPlaces);
        createHouse(requestHouse);

        Person requestPerson = PersonDAO.getPersonIdWithoutHouse();
        House requestHouses = HouseDAO.getByID(12);
        Long personId = requestPerson.getId();
        House responseHouse = HouseMethods.settle(requestHouses.getId(), requestPerson.getId());
    }
}
