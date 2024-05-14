package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
public class Car {
    @Column(name = "engine_type_id")
    private long engineTypeId;
    @Id
    private long id;
    private String mark;
    private String model;
    @Column(name = "person_id")
    private Long personId;
    @Column(columnDefinition = "numeric(12,2)")
    private Float price;
    public Car() {
    }

    public long getEngineTypeId() {
        return engineTypeId;
    }

    public void setEngineTypeId(long engineTypeId) {
        this.engineTypeId = engineTypeId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
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
                "engineTypeId=" + engineTypeId +
                ", id=" + id +
                ", mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                ", personId=" + personId +
                ", price=" + price +
                '}';
    }
}


