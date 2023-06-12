package entity;

public class Flight {
    private int id;
    private String from_location;
    private String to_location;
    private int airline_id;
    private String airline_name;
    private String departure_time;
    private String arrival_time;

    public Flight() {
    }

    public Flight(int id, String from_location, String to_location, int airline_id, String airline_name, String departure_time, String arrival_time) {
        this.id = id;
        this.from_location = from_location;
        this.to_location = to_location;
        this.airline_id = airline_id;
        this.airline_name = airline_name;
        this.departure_time = departure_time;
        this.arrival_time = arrival_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrom_location() {
        return from_location;
    }

    public void setFrom_location(String from_location) {
        this.from_location = from_location;
    }

    public String getTo_location() {
        return to_location;
    }

    public void setTo_location(String to_location) {
        this.to_location = to_location;
    }

    public int getAirline_id() {
        return airline_id;
    }

    public void setAirline_id(int airline_id) {
        this.airline_id = airline_id;
    }

    public String getAirline_name() {
        return airline_name;
    }

    public void setAirline_name(String airline_name) {
        this.airline_name = airline_name;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", from_location='" + from_location + '\'' +
                ", to_location='" + to_location + '\'' +
                ", airline_id=" + airline_id +
                ", airline_name='" + airline_name + '\'' +
                ", departure_time='" + departure_time + '\'' +
                ", arrival_time='" + arrival_time + '\'' +
                '}';
    }
}
