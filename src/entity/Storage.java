package entity;

public class Storage {
    private int id;
    private int idFlight;
    private long valume;

    public Storage() {
    }

    public Storage(int id, int idFlight, long valume) {
        this.id = id;
        this.idFlight = idFlight;
        this.valume = valume;
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

    public long getValume() {
        return valume;
    }

    public void setValume(long valume) {
        this.valume = valume;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", idFlight=" + idFlight +
                ", valume=" + valume +
                '}';
    }
}
