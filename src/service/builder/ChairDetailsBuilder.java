package service.builder;

import entity.ChairDetails;

public class ChairDetailsBuilder {
    private int id;
    private int idChair;
    private String chairName;
    private String type;

    public ChairDetailsBuilder withId(int id) {
        this.id=id;
        return this;
    }

    public ChairDetailsBuilder withIdChair(int idChair) {
        this.idChair=idChair;
        return this;
    }

    public ChairDetailsBuilder withChairName(String chairName) {
        this.chairName=chairName;
        return this;
    }

    public ChairDetailsBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public ChairDetails builder() {
        return new ChairDetails(id,idChair,chairName,type);
    }


}
