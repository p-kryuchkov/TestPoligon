package dao;

import entities.EngineType;
import org.hibernate.Session;

public class EngineTypeDAO extends DAO {
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
