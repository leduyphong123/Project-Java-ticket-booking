package entity;

public class Ticket {
    private int id;
    private int userId;
    private String title;
    private String fullName;
    private long valume;
    private String airlineCode;
    private String departureTime;
    private String airlineTime;
    private boolean status;

    public Ticket(int id, int userId,String title ,String fullName, long valume, String airlineCode, String departureTime, String airlineTime, boolean status) {
        this.id = id;
        this.userId = userId;
        this.title=title;
        this.fullName = fullName;
        this.valume = valume;
        this.airlineCode = airlineCode;
        this.departureTime = departureTime;
        this.airlineTime = airlineTime;
        this.status = status;
    }

    public Ticket() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public long getValume() {
        return valume;
    }

    public void setValume(long valume) {
        this.valume = valume;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getAirlineTime() {
        return airlineTime;
    }

    public void setAirlineTime(String airlineTime) {
        this.airlineTime = airlineTime;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        String temp;
        if (status){
            temp="check in";
        }else {
            temp="check out";
        }
        return "Ticket{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", fullName='" + fullName + '\'' +
                ", valume=" + valume +
                ", airlineCode='" + airlineCode + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", airlineTime='" + airlineTime + '\'' +
                ", status=" + temp +
                '}';
    }
}
