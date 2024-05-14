package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "house")
public class Home {
    @Column(name = "floor_count")
    private int floorCount;
    @Id
    private long id;
    @Column(columnDefinition = "numeric(19,2)")
    private Float price;

    public Home() {
    }

    public int getFloorCount() {
        return floorCount;
    }

    public void setFloorCount(int floorCount) {
        this.floorCount = floorCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Home{" +
                "floorCount=" + floorCount +
                ", id=" + id +
                ", price=" + price +
                '}';
    }
}
