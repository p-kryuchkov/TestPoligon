package entities;

import jakarta.persistence.*;

import java.util.ArrayList;
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
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "house_id")
    private List<ParkingPlace> parkingPlaces;
    @OneToMany(fetch = FetchType.EAGER)
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

    public List<Long> getLodgersIds() {
        List<Long> lodgersIds = new ArrayList<>();
        for (Person p : this.getLodgers()) lodgersIds.add(p.getId());
        return lodgersIds;
    }

    public House(int floorCount, Float price) {
        this.floorCount = floorCount;
        this.price = price;
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