package service.builder;

import entity.Flight;

public class FlightBuilder {
    private int id;
    private String from_location;
    private String to_location;
    private int airline_id;
    private String airline_name;
    private String departure_time;
    private String arrival_time;

    public FlightBuilder withIdBuilder(int id) {
        this.id = id;
        return this;
    }

    public FlightBuilder withFrom_locationBuilder(String from_location) {
        this.from_location = from_location;
        return this;
    }

    public FlightBuilder withTo_locationBuilder(String to_location) {
        this.to_location = to_location;
        return this;
    }

    public FlightBuilder withAirline_idBuilder(int airline_id) {
        this.airline_id = airline_id;
        return this;
    }

    public FlightBuilder withAirline_nameBuilder(String airline_name) {
        this.airline_name = airline_name;
        return this;
    }

    public FlightBuilder withDeparture_timeBuilder(String departure_time) {
        this.departure_time = departure_time;
        return this;
    }

    public FlightBuilder withArrival_timeBuilder(String arrival_time) {
        this.arrival_time = arrival_time;
        return this;
    }
    public Flight builder(){
        return new Flight(id,from_location,to_location,airline_id,airline_name,departure_time,arrival_time);
    }

    @Override
    public String toString() {
        return "FlightBuilder{" +
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
