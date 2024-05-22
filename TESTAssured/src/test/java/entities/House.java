package entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "house")
public class House {
    @Column(name = "floor_count")
    private int floorCount;
    @Id
    private Long id;
    @Column(columnDefinition = "numeric(19,2)")
    private Float price;
    @OneToMany
    @JoinColumn(name = "house_id")
    private List<ParkingPlace> parkingPlaces;
    @OneToMany
    @JoinColumn(name = "house_id")
    private List<Person> lodgers;

    public House() {
    }

    public int getFloorCount() {
        return floorCount;
    }

    public void setFloorCount(int floorCount) {
        this.floorCount = floorCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public List<ParkingPlace> getParkingPlaces() {
        return parkingPlaces;
    }

    public void setParkingPlaces(List<ParkingPlace> parkingPlaces) {
        this.parkingPlaces = parkingPlaces;
    }

    public List<Person> getLodgers() {
        return lodgers;
    }

    public void setLodgers(List<Person> lodgers) {
        this.lodgers = lodgers;
    }

    public House(int floorCount, Float price, List<ParkingPlace> parkingPlaces) {
        this.floorCount = floorCount;
        this.price = price;
        this.parkingPlaces = parkingPlaces;
    }

    @Override
    public String toString() {
        return "House{" +
                "floorCount=" + floorCount +
                ", id=" + id +
                ", price=" + price +
                ", parkingPlaces=" + parkingPlaces +
                ", lodgers=" + lodgers +
                '}';
    }

    public boolean equalsWithoutId(House house) {
        if (floorCount == house.floorCount
                && price.equals(house.price)) return true;
        else return false;
    }

    public boolean equalsParkingPlaces(List<ParkingPlace> parkingPlaceList) {
        boolean equalsPPL = false;
        if (parkingPlaces.size() == parkingPlaceList.size()) {
            for (ParkingPlace ppl : parkingPlaceList) {
                for (ParkingPlace pp : parkingPlaces) {
                    if (!pp.equalsWithoutId(ppl)) equalsPPL = false;
                    else {
                        equalsPPL = true;
                        break;
                    }
                }
            }
        } else equalsPPL = false;
        return equalsPPL;
    }

}