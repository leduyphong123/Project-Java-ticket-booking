package service.builder;

import entity.Acount;

public class AcountBuilder {
    private int id;
    private String email;
    private String fullName;
    private String password;
    private String type;

    public AcountBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public AcountBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public AcountBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public AcountBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public Acount builder() {
        return new Acount(id,email,fullName,password,type);
    }

    public AcountBuilder withFullName(String fullName) {
        this.fullName =fullName;
        return this;
    }
}
