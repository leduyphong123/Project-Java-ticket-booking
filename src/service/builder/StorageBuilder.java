package service.builder;

import entity.Storage;

public class StorageBuilder {
    private int id;
    private int idFlight;
    private long valume;

    public StorageBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public StorageBuilder withIdFlight(int idFlight) {
        this.idFlight = idFlight;
        return this;
    }

    public StorageBuilder withValume(long valume) {
        this.valume = valume;
        return this;
    }

    public Storage builder() {
        return new Storage(id,idFlight,valume);
    }
}
