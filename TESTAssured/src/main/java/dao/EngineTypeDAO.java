package dao;

import entities.EngineType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class EngineTypeDAO extends DAO {
    Configuration configuration = new Configuration();
    @Override
    public EngineType getByID(long id) {
        configuration.addAnnotatedClass(EngineType.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        try (SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
             Session session = sessionFactory.openSession()) {
            EngineType engineType = (EngineType) session.load(EngineType.class, id);
            System.out.println(engineType.toString());
            return engineType;
        }
    }
    @Override
    public List<EngineType> getAll() {
        configuration.addAnnotatedClass(EngineType.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        try (SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
             Session session = sessionFactory.openSession()) {
            List<EngineType> engineTypes = session.createQuery("from EngineType").list();
            return engineTypes;
        }
    }
}
