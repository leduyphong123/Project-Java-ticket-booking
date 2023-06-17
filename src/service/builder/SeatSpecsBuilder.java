package service.builder;

import entity.SeatSpecs;

public class SeatSpecsBuilder {
    private long price;
    private boolean status;
    private int id;
    private int idChair;
    private String chairName;
    private String type;

    public SeatSpecsBuilder withId(int id) {
        this.id=id;
        return this;
    }

    public SeatSpecsBuilder withIdChair(int idChair) {
        this.idChair=idChair;
        return this;
    }

    public SeatSpecsBuilder withChairName(String chairName) {
        this.chairName=chairName;
        return this;
    }

    public SeatSpecsBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public SeatSpecsBuilder withPrice(long price){
        this.price=price;
        return this;
    }

    public SeatSpecsBuilder withStatus(boolean status) {
        this.status=status;
        return this;
    }

    public SeatSpecs builder() {
        return new SeatSpecs(id,idChair,chairName,type,price,status);
    }
}
