package service.builder;

import entity.Storage;

public class StorageBuilder {
    private int id;
    private int idFlight;
    private long valume;

    public StorageBuilder withIdBuilder(int id) {
        this.id = id;
        return this;
    }

    public StorageBuilder withIdFlightBuilder(int idFlight) {
        this.idFlight = idFlight;
        return this;
    }

    public StorageBuilder withValumeBuilder(long valume) {
        this.valume = valume;
        return this;
    }

    public Storage builder() {
        return new Storage(id,idFlight,valume);
    }
}
