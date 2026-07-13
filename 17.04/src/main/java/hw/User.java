package hw;

import java.util.Date;

public class User {
    private String name;
    @OutputNullValue
    private String email;
    @DateFormat(value = "yyyy-MM-dd")
    private Date birthdate;
    @Transient
    private String password;

    public User() {}

    public User(String name, String email, Date birthdate, String password) {
        this.name = name;
        this.email = email;
        this.birthdate = birthdate;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthdate=" + birthdate +
                ", password='" + password + '\'' +
                '}';
    }
}
