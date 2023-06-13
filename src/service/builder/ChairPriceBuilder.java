package service.builder;

import entity.ChairPrice;

public class ChairPriceBuilder {
    private int id;
    private String type;
    private long price;

    public ChairPriceBuilder withIdBuilder(int id) {
        this.id = id;
        return this;
    }

    public ChairPriceBuilder withTypeBuilder(String type) {
        this.type =type;
        return this;
    }

    public ChairPriceBuilder withPriceBuilder(long price) {
        this.price = price;
        return this;
    }

    public ChairPrice builder() {
        return new ChairPrice(id,type,price);
    }
}
