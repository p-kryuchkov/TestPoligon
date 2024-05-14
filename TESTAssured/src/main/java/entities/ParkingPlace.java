package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "parking_place")
public class ParkingPlace {
    @Id
    private long id;
    private boolean is_covered;
    private boolean is_warm;
    private int places_count;
    private Long  house_id;

    public ParkingPlace() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isIs_covered() {
        return is_covered;
    }

    public void setIs_covered(boolean is_covered) {
        this.is_covered = is_covered;
    }

    public boolean isIs_warm() {
        return is_warm;
    }

    public void setIs_warm(boolean is_warm) {
        this.is_warm = is_warm;
    }

    public int getPlaces_count() {
        return places_count;
    }

    public void setPlaces_count(int places_count) {
        this.places_count = places_count;
    }

    public Long getHouse_id() {
        return house_id;
    }

    public void setHouse_id(Long house_id) {
        this.house_id = house_id;
    }

    @Override
    public String toString() {
        return "ParkingPlace{" +
                "id=" + id +
                ", is_covered=" + is_covered +
                ", is_warm=" + is_warm +
                ", places_count=" + places_count +
                ", house_id=" + house_id +
                '}';
    }
}
