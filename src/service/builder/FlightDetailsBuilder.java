package service.builder;

import entity.ChairPrice;
import entity.FlightDetails;

import java.util.List;

public class FlightDetailsBuilder {
    private int id;
    private int idFlight;
    private String date;
    private long storageValume;
    private long usedStorageValume;
    public FlightDetailsBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public FlightDetailsBuilder withIdFlight(int idFlight) {
        this.idFlight = idFlight;
        return this;
    }

    public FlightDetailsBuilder withDate(String date) {
        this.date = date;
        return this;
    }

    public FlightDetailsBuilder withStorageValume(long storageValume) {
        this.storageValume = storageValume;
        return this;
    }

    public FlightDetailsBuilder withUsedStorageValume(long usedStorageValume) {
        this.usedStorageValume = usedStorageValume;
        return this;
    }
    public FlightDetails builder() {
        return new FlightDetails(id, idFlight, date, storageValume, usedStorageValume);
    }
}
