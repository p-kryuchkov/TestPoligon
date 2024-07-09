package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "parking_place")
public class ParkingPlace {
    @Id
    private Long id;
    @Column(name = "is_covered")
    private Boolean isCovered;
    @Column(name = "is_warm")
    private Boolean isWarm;
    @Column(name = "places_count")
    private int placesCount;
    @Column(name = "house_id")
    private Long houseId;

    public ParkingPlace() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isCovered() {
        return isCovered;
    }

    public void setCovered(Boolean covered) {
        isCovered = covered;
    }

    public Boolean isWarm() {
        return isWarm;
    }

    public void setWarm(Boolean warm) {
        isWarm = warm;
    }

    public int getPlacesCount() {
        return placesCount;
    }

    public void setPlacesCount(int placesCount) {
        this.placesCount = placesCount;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public ParkingPlace(boolean isCovered, boolean isWarm, int placesCount) {
        this.isCovered = isCovered;
        this.isWarm = isWarm;
        this.placesCount = placesCount;
    }

    public boolean equalsWithoutId(ParkingPlace parkingPlace) {
        if (isCovered.equals(parkingPlace.isCovered
                && isWarm.equals(parkingPlace.isWarm))
                && placesCount == parkingPlace.placesCount) return true;
        else return false;
    }

    @Override
    public String toString() {
        return "ParkingPlace{" +
                "id=" + id +
                ", isCovered=" + isCovered +
                ", isWarm=" + isWarm +
                ", placesCount=" + placesCount +
                ", houseId=" + houseId +
                '}';
    }
}
