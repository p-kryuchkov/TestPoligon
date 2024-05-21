package entities;

import dao.EngineTypeDAO;
import jakarta.persistence.*;
import entities.EngineType;

import java.util.Objects;

@Entity
public class Car {
    @ManyToOne
    @JoinColumn(name = "engine_type_id")
    private EngineType engineType;
    @Id
    private Long id;
    private String mark;
    private String model;
    @Column(name = "person_id")
    private Long person;
    @Column(columnDefinition = "numeric(12,2)")
    private Float price;
    public Car() {
    }

    public Car(EngineType engineType, String mark, String model, Float price) {

        this.engineType = engineType;
        this.mark = mark;
        this.model = model;
        this.price = price;
    }
    public Car(String engineType, String mark, String model, Float price) {
        this.engineType = EngineTypeDAO.getByName(engineType);
        this.mark = mark;
        this.model = model;
        this.price = price;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }
    public void setEngineType(String engineType) {this.engineType = EngineTypeDAO.getByName(engineType);}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getPerson() {
        return person;
    }

    public void setPerson(Long person) {
        this.person = person;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
    @Override
    public String toString() {
        return "Car{" +
                "engineType='" + engineType + '\'' +
                ", id=" + id +
                ", mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                ", Person=" + person +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id &&
                Double.compare(car.price, price) == 0 &&
                Objects.equals(car.engineType, engineType) &&
                Objects.equals(mark, car.mark) &&
                Objects.equals(model, car.model) &&
                Objects.equals(person, car.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(engineType, id, mark, model, person, price);
    }

    public boolean equalsWithotId(Car car) {
        if (engineType.equals(car.getEngineType()) &&
                mark.equals(car.mark) &&
                model.equals(car.model) &&
                price.equals(car.price))
            return true;
        else return false;
    }
}


