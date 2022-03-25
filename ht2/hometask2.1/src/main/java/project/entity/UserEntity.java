package project.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String login;
    private String password;
    private String name;
    private LocalDate birthDay;


    @OneToMany(cascade = CascadeType.ALL,fetch =FetchType.LAZY)
    @JoinColumn(name = "user_audit")
    private List<AuditUserEntity> userAudits=new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "login_receiver")
    private List<MessageEntity> messageEntities=new ArrayList<>();


    public UserEntity(Long id,String birthDay, String login, String name,String password) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        if (birthDay==null||Objects.equals(birthDay, "")) {this.birthDay = null;}
        else this.birthDay=LocalDate.parse(birthDay) ;
    }

    public UserEntity(String login, String password, String name, String birthDay) {
        this.login = login;
        this.password = password;
        this.name = name;
        if (birthDay!=null&&!Objects.equals(birthDay, "")) this.birthDay = LocalDate.parse(birthDay);
    }

    public UserEntity() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity userEntity = (UserEntity) o;
        return Objects.equals(login, userEntity.login) && Objects.equals(password, userEntity.password) && Objects.equals(name, userEntity.name) && Objects.equals(birthDay, userEntity.birthDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, name, birthDay);
    }

    public Long getId() {
        return id;
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

    public List<MessageEntity> getMessages() {
        return messageEntities;
    }

    public void setMessages(List<MessageEntity> messageEntities) {
        this.messageEntities = messageEntities;
    }

    public void setId(Long id) {
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
    public List<AuditUserEntity> getUserAudits() {
        return userAudits;
    }

    public void setUserAudits(List<AuditUserEntity> userAudits) {
        this.userAudits = userAudits;
    }

    public List<MessageEntity> getMessageEntities() {
        return messageEntities;
    }

    public void setMessageEntities(List<MessageEntity> messageEntities) {
        this.messageEntities = messageEntities;
    }
}
