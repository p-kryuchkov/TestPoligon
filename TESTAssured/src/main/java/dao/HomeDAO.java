package dao;

import entities.Home;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HomeDAO extends DAO {
    Configuration configuration = new Configuration();
    @Override
    public Home getByID(long id) {
        configuration.addAnnotatedClass(Home.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        try (SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
             Session session = sessionFactory.openSession()) {
            Home home = (Home) session.load(Home.class, id);
            System.out.println(home.toString());
            return home;
        }
    }
    @Override
    public List<Home> getAll() {
        configuration.addAnnotatedClass(Home.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        try (SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
             Session session = sessionFactory.openSession()) {
            List<Home> homes = session.createQuery("from Home").list();
            return homes;
        }
    }
}


