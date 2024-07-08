package dao;

import entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class DAO<T> {
    static SessionFactory sessionFactory;
     Class<T> type = null;

    public DAO(Class<T> type) {
        this.type = type;
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(type);
 //ToDo переделай этот позор
        configuration.addAnnotatedClass(EngineType.class);
        configuration.addAnnotatedClass(Person.class);
        configuration.addAnnotatedClass(Car.class);
        configuration.addAnnotatedClass(House.class);
        configuration.addAnnotatedClass(ParkingPlace.class);
        configuration.addPackage("entities");
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(builder.build());
    }
    public T getByID(long id) {
        try (Session session = sessionFactory.openSession()) {
            T entity = session.get(type, id);
            return entity;
        }
    }
    public List<T> getAll() {
        try (Session session = sessionFactory.openSession()){
            return session.createQuery("from "+ type.getName()).getResultList();
        }
    }
    public Long getAllSize() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from " + type.getName()).getResultCount();
        }
    }
    public T getValueByFieldName(String field, Object value) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("from " + type.getName() + " where " + field + " = :value")
                    .setParameter("value", value);
            return (T) query.uniqueResult();
        }
    }
}
