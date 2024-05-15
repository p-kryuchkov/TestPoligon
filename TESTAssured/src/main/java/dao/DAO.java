package dao;

import entities.Car;

import java.util.List;
import java.util.Objects;

abstract class DAO {
    public abstract Object getByID(long id);

    public abstract List getAll();

}
