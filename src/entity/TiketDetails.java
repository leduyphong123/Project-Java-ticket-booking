package entity;

public class TiketDetails {
    private int ticketId;
    private String email;
    private String title;
    private String lastName;
    private String firtName;
    private String dateOfBirth;
    private String nationality;
    private String payment;

    public TiketDetails() {
    }

    public TiketDetails(int ticketId, String email, String title, String lastName, String firtName, String dateOfBirth, String nationality, String payment) {
        this.ticketId = ticketId;
        this.email = email;
        this.title = title;
        this.lastName = lastName;
        this.firtName = firtName;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
        this.payment = payment;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirtName() {
        return firtName;
    }

    public void setFirtName(String firtName) {
        this.firtName = firtName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "TiketDetails{" +
                "ticketId=" + ticketId +
                ", email='" + email + '\'' +
                ", title='" + title + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firtName='" + firtName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", nationality='" + nationality + '\'' +
                ", payment='" + payment + '\'' +
                '}';
    }
}
