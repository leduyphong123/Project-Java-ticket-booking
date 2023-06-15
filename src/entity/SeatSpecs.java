package entity;

public class SeatSpecs extends ChairDetails{
    private long price;
    private boolean status;
    public SeatSpecs(){}
    public SeatSpecs(int id, int idChair, String chairName, String type, long price, boolean status) {
        super(id, idChair, chairName, type);
        this.price = price;
        this.status = status;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SeatSpecs{"
                +super.toString()+
                "price=" + price +
                ", status=" + status +
                '}';
    }
}
