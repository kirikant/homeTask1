package project.storages.api;

import project.entity.AuditUserEntity;
import project.entity.MessageEntity;
import project.entity.UserEntity;

import java.util.List;

public interface IChatStorageWithAudit {
    List<AuditUserEntity> getUserAudits (String login);
    void addUser(UserEntity user, AuditUserEntity auditUserEntity);
    void addMessage(MessageEntity message, AuditUserEntity auditUserEntity);
    long getUsersNumber();
    long getMessagesNumber();
    boolean haveUserExistence(String login);
    UserEntity getUser(String login);
    List<MessageEntity> getUserMessages(String login);
    boolean checkPassword(String login, String password);
}
