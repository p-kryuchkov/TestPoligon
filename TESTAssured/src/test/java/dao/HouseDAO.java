package dao;

import entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HouseDAO extends DAO {
       public HouseDAO() {
        super(House.class);
    }
}


