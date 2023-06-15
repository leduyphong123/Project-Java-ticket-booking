package service.builder;

import entity.Ticket;

public class TicketBuilder {
    private int id;
    private int userId;
    private String fullName;
    private long valume;
    private String airlineCode;
    private String departureTime;
    private String airlineTime;
    private boolean status;

    public TicketBuilder withIdBuilder(int id) {
        this.id = id;
        return this;
    }

    public TicketBuilder withUserIdBuilder(int userId) {
        this.userId = userId;
        return this;
    }

    public TicketBuilder withFullName(String fullName) {
        this.fullName=fullName;
        return this;
    }

    public TicketBuilder withValumeBuilder(long valume) {
        this.valume=valume;
        return this;
    }

    public TicketBuilder withAirlineCode(String airlineCode) {
        this.airlineCode=airlineCode;
        return this;
    }

    public TicketBuilder withDepartureTimeBuilder(String departureTime) {
        this.departureTime=departureTime;
        return this;
    }

    public TicketBuilder withAirlineTimeBuilder(String arrivalTime) {
        this.airlineTime=arrivalTime;
        return this;
    }

    public TicketBuilder withStatusBuilder(boolean status) {
        this.status=status;
        return this;
    }

    public Ticket builder() {
        return new Ticket(id,userId,fullName,valume,airlineCode,departureTime,airlineTime,status);
    }
}
