package project.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class Message {
    private long id;
    private LocalDateTime dateTime;
    private String loginSender;
    private String text;
    private String loginReceiver;


    public Message(LocalDateTime dateTime, String loginSender, String text, String loginReceiver) {
        this.dateTime = dateTime;
        this.loginSender = loginSender;
        this.text = text;
        this.loginReceiver = loginReceiver;
    }

    public Message(long id, LocalDateTime dateTime, String loginSender, String text, String loginReceiver) {
        this.id = id;
        this.dateTime = dateTime;
        this.loginSender = loginSender;
        this.text = text;
        this.loginReceiver = loginReceiver;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getLoginSender() {
        return loginSender;
    }

    public String getText() {
        return text;
    }

    public String getLoginReceiver() {
        return loginReceiver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(dateTime, message.dateTime) && Objects.equals(loginSender, message.loginSender) && Objects.equals(text, message.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime, loginSender, text);
    }

    @Override
    public String toString() {
        return "Message from " + loginSender +" : "+ text +"  Message was sent:"+dateTime;
    }
}