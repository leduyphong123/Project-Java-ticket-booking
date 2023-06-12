package entity;

public class ChairDetails {
    private int id;
    private int idChair;
    private String chairName;
    private String type;

    public ChairDetails() {
    }

    public ChairDetails(int id, int idChair, String chairName, String type) {
        this.id = id;
        this.idChair = idChair;
        this.chairName = chairName;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdChair() {
        return idChair;
    }

    public void setIdChair(int idChair) {
        this.idChair = idChair;
    }

    public String getChairName() {
        return chairName;
    }

    public void setChairName(String chairName) {
        this.chairName = chairName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ChairDetails{" +
                "id=" + id +
                ", idChair=" + idChair +
                ", chairName='" + chairName + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
