package entities;

public class EngineType {
    ;
    private int id;
    private String type_name;

    public int getId() {
        return id;
    }

    public String getType_name() {
        return type_name;
    }

    EngineType(int id, String type_name) {
        this.id = id;
        this.type_name = type_name;
    }


}
