package entity;

public class LuggagePrice {
    private int id;
    private String name;
    private long valume;
    private long price;

    public LuggagePrice(int id, String name, long valume, long price) {
        this.id = id;
        this.name = name;
        this.valume = valume;
        this.price = price;
    }

    public long getValume() {
        return valume;
    }

    public void setValume(long valume) {
        this.valume = valume;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getPrice() {
        return price;
    }

    public LuggagePrice() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "LuggagePrice{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", valume=" + valume +
                ", price=" + price +
                '}';
    }
}
