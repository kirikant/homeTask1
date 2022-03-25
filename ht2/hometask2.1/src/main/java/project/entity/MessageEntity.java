package project.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "messages")
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime dateTime;
    private String loginReceiver;
    private String loginSender;
    private String text;

    public MessageEntity() {
    }

    public MessageEntity(Long id, LocalDateTime dateTime,String loginReceiver ,String loginSender ,String text) {
        this.id = id;
        this.dateTime = dateTime;
        this.loginSender = loginSender;
        this.text = text;
        this.loginReceiver = loginReceiver;
    }

    public MessageEntity(LocalDateTime dateTime, String loginSender, String text, String loginReceiver) {
        this.dateTime = dateTime;
        this.loginSender = loginSender;
        this.text = text;
        this.loginReceiver=loginReceiver;
    }

    public MessageEntity(LocalDateTime dateTime, String loginSender, String text) {
        this.dateTime = dateTime;
        this.loginSender = loginSender;
        this.text = text;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setLoginSender(String loginSender) {
        this.loginSender = loginSender;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLoginReceiver() {
        return loginReceiver;
    }

    public void setLoginReceiver(String loginReceiver) {
        this.loginReceiver = loginReceiver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageEntity messageEntity = (MessageEntity) o;
        return Objects.equals(dateTime, messageEntity.dateTime) && Objects.equals(loginSender, messageEntity.loginSender) && Objects.equals(text, messageEntity.text);
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
