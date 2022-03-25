package project.entity;

import project.dto.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "audits")
public class AuditUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dtCreate;
    private String text;
    private String userAudit;
    private String author;

    public AuditUserEntity() {
    }

    public AuditUserEntity(Long id, LocalDateTime dtCreate, String text, String user, String author) {
        this.id = id;
        this.dtCreate = dtCreate;
        this.text = text;
        this.userAudit = user;
        this.author = author;
    }

    public AuditUserEntity(LocalDateTime dtCreate, String text, String user, String author) {
        this.dtCreate = dtCreate;
        this.text = text;
        this.userAudit = user;
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuditUserEntity that = (AuditUserEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(dtCreate, that.dtCreate) && Objects.equals(text, that.text) && Objects.equals(userAudit, that.userAudit) && Objects.equals(author, that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dtCreate, text, userAudit, author);
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
        return userAudit;
    }

    public void setUser(String user) {
        this.userAudit = user;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}