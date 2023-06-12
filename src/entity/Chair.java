package entity;

public class Chair {
    private int id;
    private int idFlight;
    private int lineQuantity;

    public Chair() {
    }

    public Chair(int id, int idFlight, int lineQuantity) {
        this.id = id;
        this.idFlight = idFlight;
        this.lineQuantity = lineQuantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdFlight() {
        return idFlight;
    }

    public void setIdFlight(int idFlight) {
        this.idFlight = idFlight;
    }

    public int getLineQuantity() {
        return lineQuantity;
    }

    public void setLineQuantity(int lineQuantity) {
        this.lineQuantity = lineQuantity;
    }

    @Override
    public String toString() {
        return "Chair{" +
                "id=" + id +
                ", idFlight=" + idFlight +
                ", lineName=" + lineQuantity +
                '}';
    }
}
