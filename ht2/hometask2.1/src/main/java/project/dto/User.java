package project.dto;

import java.time.LocalDate;
import java.util.Objects;

public class User {
    private long id;
    private String login;
    private String password;
    private String name;
    private LocalDate birthDay;

    public User(String login, String password, String name, String birthDay) {
        this.login = login;
        this.password = password;
        this.name = name;
        if (!(Objects.equals(birthDay, "")||birthDay==null))  this.birthDay = LocalDate.parse(birthDay);
    }

    public User(long id, String login, String password, String name, String birthDay) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        if (!(Objects.equals(birthDay, "")||birthDay==null))  this.birthDay = LocalDate.parse(birthDay);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(name, user.name) && Objects.equals(birthDay, user.birthDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, name, birthDay);
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
    public LocalDate getBirthDay() {
        return birthDay;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", birthDay=" + birthDay +
                '}';
    }
}