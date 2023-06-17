package service.builder;

import entity.LuggagePrice;

public class LuggagePriceBuilder {
    private int id;
    private String name;
    private long valume;

    private long price;

    public LuggagePriceBuilder withId(int id) {
        this.id=id;
        return this;
    }

    public LuggagePriceBuilder withName(String name) {
        this.name=name;
        return this;
    }

    public LuggagePriceBuilder withPrice(long price) {
        this.price=price;
        return this;
    }

    public LuggagePrice builder() {
        return new LuggagePrice(id,name,valume,price);
    }

    public LuggagePriceBuilder withValume(long valume) {
        this.valume=valume;
        return this;
    }
}
