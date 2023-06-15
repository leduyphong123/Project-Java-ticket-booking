package service.builder;

import entity.Acount;

public class AcountBuilder {
    private int id;
    private String email;
    private String password;
    private String type;

    public AcountBuilder withIdBuilder(int id) {
        this.id = id;
        return this;
    }

    public AcountBuilder withEmailBuilder(String email) {
        this.email = email;
        return this;
    }

    public AcountBuilder withPasswordBuilder(String password) {
        this.password = password;
        return this;
    }

    public AcountBuilder withTypeBuilder(String type) {
        this.type = type;
        return this;
    }

    public Acount builder() {
        return new Acount(id,email,password,type);
    }
}
