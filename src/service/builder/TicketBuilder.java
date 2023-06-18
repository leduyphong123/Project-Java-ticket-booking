package service.builder;

import entity.Ticket;

public class TicketBuilder {
    private int id;
    private int userId;
    private String title;
    private String fullName;
    private long valume;
    private String airlineCode;
    private String departureTime;
    private String airlineTime;

    private boolean status;

    public TicketBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public TicketBuilder withUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public TicketBuilder withFullName(String fullName) {
        this.fullName=fullName;
        return this;
    }

    public TicketBuilder withValume(long valume) {
        this.valume=valume;
        return this;
    }

    public TicketBuilder withAirlineCode(String airlineCode) {
        this.airlineCode=airlineCode;
        return this;
    }

    public TicketBuilder withDepartureTime(String departureTime) {
        this.departureTime=departureTime;
        return this;
    }

    public TicketBuilder withAirlineTime(String arrivalTime) {
        this.airlineTime=arrivalTime;
        return this;
    }

    public TicketBuilder withStatus(boolean status) {
        this.status=status;
        return this;
    }
    public TicketBuilder withTitle(String title) {
        this.title = title;
        return this;
    }
    public Ticket builder() {
        return new Ticket(id,userId,title,fullName,valume,airlineCode,departureTime,airlineTime,status);
    }


}
