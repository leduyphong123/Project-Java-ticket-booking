package service;

import entity.ChairDetails;

public class ChairDetailsBuilder {
    private int id;
    private int idChair;
    private String chairName;
    private String type;

    public ChairDetailsBuilder withIdBuilder(int id) {
        this.id=id;
        return this;
    }

    public ChairDetailsBuilder withIdChairBuilder(int idChair) {
        this.idChair=idChair;
        return this;
    }

    public ChairDetailsBuilder withChairNameBuilder(String chairName) {
        this.chairName=chairName;
        return this;
    }

    public ChairDetailsBuilder withTypeBuilder(String type) {
        this.type = type;
        return this;
    }

    public ChairDetails builder() {
        return new ChairDetails(id,idChair,chairName,type);
    }
}
