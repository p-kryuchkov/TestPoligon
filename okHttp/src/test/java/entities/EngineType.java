package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "engine_type")
public class EngineType {
    @Id
    private Long id;
    private String type_name;

    public long getId() {
        return id;
    }

    public void setId(Long id) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EngineType engineType = (EngineType) o;
        return this.id.equals(engineType.id) && this.type_name.equals(engineType.type_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type_name);
    }

    @Override
    public String toString() {
        return "EngineType{" +
                "id=" + id +
                ", type_name='" + type_name + '\'' +
                '}';
    }
}
