package entity;

import java.util.List;

public class FlightDetails {
    private int id;
    private int idFlight;
    private String date;
    private long storageValume;
    private long usedStorageValume;

    public FlightDetails() {
    }

    public FlightDetails(int id, int idFlight, String date, long storageValume, long usedStorageValume){
        this.id=id;
        this.idFlight = idFlight;
        this.date = date;
        this.storageValume = storageValume;
        this.usedStorageValume = usedStorageValume;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getStorageValume() {
        return storageValume;
    }

    public void setStorageValume(long storageValume) {
        this.storageValume = storageValume;
    }

    public long getUsedStorageValume() {
        return usedStorageValume;
    }

    public void setUsedStorageValume(long usedStorageValume) {
        this.usedStorageValume = usedStorageValume;
    }



    @Override
    public String toString() {
        return "FlightDetails{" +
                "id=" + id +
                ", idFlight=" + idFlight +
                ", date='" + date + '\'' +
                ", storageValume='" + storageValume + '\'' +
                ", usedStorageValume='" + usedStorageValume + '\'' +
                '}';
    }
}
