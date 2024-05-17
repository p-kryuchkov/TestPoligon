package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "engine_type")
public class EngineType {
    @Id
    private long id;
    private String type_name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public EngineType() {
    }

    @Override
    public String toString() {
        return "EngineType{" +
                "id=" + id +
                ", type_name='" + type_name + '\'' +
                '}';
    }
}
