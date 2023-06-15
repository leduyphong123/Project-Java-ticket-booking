package service.builder;

import entity.LuggagePrice;

public class LuggagePriceBuilder {
    private int id;
    private String name;
    private long valume;

    private long price;

    public LuggagePriceBuilder withIdBuilder(int id) {
        this.id=id;
        return this;
    }

    public LuggagePriceBuilder withNameBuilder(String name) {
        this.name=name;
        return this;
    }

    public LuggagePriceBuilder withPriceBuilder(long price) {
        this.price=price;
        return this;
    }

    public LuggagePrice builder() {
        return new LuggagePrice(id,name,valume,price);
    }

    public LuggagePriceBuilder withValumeBuilder(long valume) {
        this.valume=valume;
        return this;
    }
}
