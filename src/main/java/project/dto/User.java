package project.dto;

import java.time.LocalDate;
import java.util.Objects;

public class User {
    private String login;
    private String password;
    private String name;
    private LocalDate birthDay;

    public User(String login, String password, String name, String birthDay) {
        this.login = login;
        this.password = password;
        this.name = name;
        if (!Objects.equals(birthDay, ""))  this.birthDay = LocalDate.parse(birthDay);
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
}
