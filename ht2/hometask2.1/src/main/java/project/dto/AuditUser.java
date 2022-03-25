package project.dto;

import project.entity.AuditUserEntity;

import java.time.LocalDateTime;
import java.util.Objects;

public class AuditUser {
    private Long id;
    private LocalDateTime dtCreate;
    private String text;
    private String user;
    private String author;

    public AuditUser() {
    }

    public AuditUser(Long id, LocalDateTime dtCreate, String text, String user, String author) {
        this.id = id;
        this.dtCreate = dtCreate;
        this.text = text;
        this.user = user;
        this.author = author;
    }

    public AuditUser(LocalDateTime dtCreate, String text, String user, String author) {
        this.dtCreate = dtCreate;
        this.text = text;
        this.user = user;
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuditUser auditUser = (AuditUser) o;
        return Objects.equals(id, auditUser.id) && Objects.equals(dtCreate, auditUser.dtCreate) && Objects.equals(text, auditUser.text) && Objects.equals(user, auditUser.user) && Objects.equals(author, auditUser.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dtCreate, text, user, author);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "AuditUser{" +
                "id=" + id +
                ", dtCreate=" + dtCreate +
                ", text='" + text + '\'' +
                ", user='" + user + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
