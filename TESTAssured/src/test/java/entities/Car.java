package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class Car {
    @Transient
    private String engineType;
    @Column(name = "engine_type_id")
    private Long engineTypeId;
    @Id
    private Long id;
    private String mark;
    private String model;
    @Column(name = "person_id")
    private Long Person;
    @Column(columnDefinition = "numeric(12,2)")
    private Float price;
    public Car() {
    }

    public Car(String engineType, String mark, String model, Float price) {
        this.engineType = engineType;
        this.mark = mark;
        this.model = model;
        this.price = price;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public Long getEngineTypeId() {
        return engineTypeId;
    }

    public void setEngineTypeId(Long engineTypeId) {
        this.engineTypeId = engineTypeId;
    }

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
        return Person;
    }

    public void setPerson(Long person) {
        Person = person;
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
                ", engineTypeId=" + engineTypeId +
                ", id=" + id +
                ", mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                ", Person=" + Person +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
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


