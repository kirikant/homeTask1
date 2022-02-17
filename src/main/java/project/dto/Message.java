package project.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class Message {
    private LocalDateTime dateTime;
    private String login;
    private String text;

    public Message(LocalDateTime dateTime, String login, String text) {
        this.dateTime = dateTime;
        this.login = login;
        this.text = text;
    }


    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getLogin() {
        return login;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(dateTime, message.dateTime) && Objects.equals(login, message.login) && Objects.equals(text, message.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime, login, text);
    }

    @Override
    public String toString() {
        return "Message from " +login+" : "+ text +"  Message was sent:"+dateTime;
    }
}
