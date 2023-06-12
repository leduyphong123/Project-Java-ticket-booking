package service;

import entity.Chair;

public class ChairBuilder {
    private int id;
    private int idFlight;
    private int lineQuantity;

    public ChairBuilder withIdBuilder(int id) {
        this.id = id;
        return this;
    }

    public ChairBuilder withIdFlightBuilder(int idFlight) {
        this.idFlight = idFlight;
        return this;
    }

    public ChairBuilder withLineQuantityBuilder(int lineQuantity) {
        this.lineQuantity = lineQuantity;
        return this;
    }
    public Chair builder(){
        return new Chair(id,idFlight,lineQuantity);
    }

    @Override
    public String toString() {
        return "ChairBuilder{" +
                "id=" + id +
                ", idFlight=" + idFlight +
                ", lineQuantity=" + lineQuantity +
                '}';
    }
}
