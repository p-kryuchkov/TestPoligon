import entities.*;
import entities.Car;
import entities.EngineType;
import entities.Home;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class HibernateRunner {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
     // configuration.addPackage("entities");
       configuration.addAnnotatedClass(Car.class);
       configuration.addAnnotatedClass(EngineType.class);
       configuration.addAnnotatedClass(Home.class);
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

            Home home = (Home) session.load(Home.class,2);
            System.out.println(home.toString());

            ParkingPlace parkingPlace = (ParkingPlace) session.load(ParkingPlace.class,3);
            System.out.println(parkingPlace.toString());

            Person person = (Person) session.load(Person.class,2);
            System.out.println(person.toString());


            }
        }
    }




