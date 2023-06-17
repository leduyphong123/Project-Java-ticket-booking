package service.builder;

import entity.TiketDetails;

public class TiketDetailsBuilder {
    private int ticketId;
    private String email;
    private String title;
    private String lastName;
    private String firtName;
    private String dateOfBirth;
    private String nationality;
    private String payment;

    public TiketDetailsBuilder withTicketId(int ticketId) {
        this.ticketId=ticketId;
        return this;
    }

    public TiketDetailsBuilder withEmail(String email) {
        this.email=email;
        return this;
    }

    public TiketDetailsBuilder withTitle(String title) {
        this.title=title;
        return this;
    }

    public TiketDetailsBuilder withLastName(String lastName) {
        this.lastName=lastName;
        return this;
    }

    public TiketDetailsBuilder withFirtName(String firtName) {
        this.firtName=firtName;
        return this;
    }

    public TiketDetailsBuilder withDateOfBirth(String dateOfBirth) {
        this.dateOfBirth=dateOfBirth;
        return this;
    }

    public TiketDetailsBuilder withNationality(String nationality) {
        this.nationality=nationality;
        return this;
    }

    public TiketDetailsBuilder withPayment(String payment) {
        this.payment=payment;
        return this;
    }

    public TiketDetails builder() {
        return new TiketDetails(ticketId,email,title,lastName,firtName,dateOfBirth,nationality,payment);
    }
}
