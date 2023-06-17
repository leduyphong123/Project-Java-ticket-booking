package entity;

public class Acount {
    private int id;
    private String email;
    private String fullName;
    private String password;
    private String type;

    public Acount() {
    }

    public Acount(int id, String email,String fullName ,String password, String type) {
        this.id = id;
        this.email = email;
        this.fullName=fullName;
        this.password = password;
        this.type = type;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Acount{" +
                "email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
