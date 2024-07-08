package dao;

import entities.Car;
import entities.EngineType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class EngineTypeDAO extends DAO{
       public EngineTypeDAO() {
        super(EngineType.class);
    }
public static EngineType getByName(String name) {
    try (Session session = sessionFactory.openSession()) {
        EngineType engineType = (EngineType) session.createQuery("from EngineType where type_name = :param").setParameter("param", name).uniqueResult();
        System.out.println(engineType.toString());
        return engineType;
    }
}
}
