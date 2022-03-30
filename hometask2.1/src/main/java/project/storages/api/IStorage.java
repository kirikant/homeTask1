package project.storages.api;

import project.dto.Pageable;
import project.entity.MessageEntity;
import project.entity.UserEntity;

import javax.persistence.EntityManager;
import java.util.List;

public interface IStorage<T> {
    void addUser(UserEntity user,T t);
    void addMessage(MessageEntity message,T t);
    long getUsersNumber();
    long getMessagesNumber();
    boolean haveUserExistence(String login);
    List<MessageEntity> getUserMessages(UserEntity userEntity, Pageable pageable);
    boolean checkPassword(String login, String password);
    UserEntity getUser(String login,T t);
}
