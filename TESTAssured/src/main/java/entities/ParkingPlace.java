package entities;

public class ParkingPlace {
    private int id;
    private boolean is_covered;
    private boolean is_warm;
    private int places_count;
    private int  house_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getHouse_id() {
        return house_id;
    }

    public void setHouse_id(int house_id) {
        this.house_id = house_id;
    }

    public ParkingPlace(int id, boolean is_covered, boolean is_warm, int places_count, int house_id) {
        this.id = id;
        this.is_covered = is_covered;
        this.is_warm = is_warm;
        this.places_count = places_count;
        this.house_id = house_id;
    }
}
