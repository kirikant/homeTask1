package project.storages.api;

import project.entity.AuditUserEntity;
import project.entity.MessageEntity;
import project.entity.UserEntity;

import java.util.List;

public interface IStorage {
    void addUser(UserEntity user);
    void addMessage(MessageEntity message);
    long getUsersNumber();
    long getMessagesNumber();
    boolean haveUserExistence(String login);
    UserEntity getUser(String login);
    List<MessageEntity> getUserMessages(String login);
    boolean checkPassword(String login, String password);

}
