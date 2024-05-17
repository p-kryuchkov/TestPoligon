import dao.HouseDAO;
import entities.*;
import entities.Car;
import entities.EngineType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HibernateRunner {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
     // configuration.addPackage("entities");
       configuration.addAnnotatedClass(Car.class);
       configuration.addAnnotatedClass(EngineType.class);
       configuration.addAnnotatedClass(House.class);
       configuration.addAnnotatedClass(ParkingPlace.class);
       configuration.addAnnotatedClass(Person.class);


        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());


        try (SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
             Session session = sessionFactory.openSession()){

            System.out.println("OK");
         //   Query<Car> query = session.createQuery("from Car", Car.class);
Car car = (Car) session.load(Car.class, 3);
            System.out.println(car.toString());

        //    Query<EngineType> queryEngine = session.createQuery("from engine_type", EngineType.class);
            EngineType engineType = (EngineType) session.load(EngineType.class, 3);
            System.out.println(engineType.toString());

            House house = (House) session.load(House.class,2);
            System.out.println(house.toString());

            ParkingPlace parkingPlace = (ParkingPlace) session.load(ParkingPlace.class,3);
            System.out.println(parkingPlace.toString());

            Person person = (Person) session.load(Person.class,2);
            System.out.println(person.toString());

            HouseDAO result = new HouseDAO();
            result.getByID(2);

            House resultcar = result.getByID(3);
            System.out.println(resultcar.toString());

            List listcar = result.getAll();
            System.out.println(listcar.size());


        }
        }
    }




