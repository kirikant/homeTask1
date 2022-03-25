package project.storages.hib;

import project.entity.AuditUserEntity;
import project.entity.MessageEntity;
import project.entity.UserEntity;
import project.storages.api.IChatStorageWithAudit;
import project.utils.EntityFactoryInitializer;

import java.util.List;

public class HibChatStorageWithAudit implements IChatStorageWithAudit {
    private EntityFactoryInitializer factoryInitializer;

    private HibChatStorageWithAudit() {
        factoryInitializer = EntityFactoryInitializer.getInstance();
    }

    private HibStorage hibStorage = HibStorage.getHibStorage();
    private HibAuditStorage hibAuditStorage = HibAuditStorage.getHibAuditStorage();

    private static HibChatStorageWithAudit hibChatStorageWithAudit = new HibChatStorageWithAudit();

    public static HibChatStorageWithAudit getHibChatStorageWithAudit() {
        return hibChatStorageWithAudit;
    }


    public void addUser(UserEntity userEntity, AuditUserEntity auditUserEntity) {
        userEntity.getUserAudits().add(auditUserEntity);
        hibStorage.addUser(userEntity);
    }

    public void addMessage(MessageEntity messageEntity, AuditUserEntity auditUserEntity) {
        hibStorage.addMessage(messageEntity);
        UserEntity userEntity = hibStorage.getUser(messageEntity.getLoginReceiver());
        userEntity.getUserAudits().add(auditUserEntity);
        hibStorage.addUser(userEntity);

    }

    public List<AuditUserEntity> getUserAudits(String login) {
        return hibAuditStorage.readAudit(login);
    }

    public long getUsersNumber() {
        return hibStorage.getUsersNumber();
    }

    public long getMessagesNumber() {
        return hibStorage.getMessagesNumber();
    }

    public boolean haveUserExistence(String login) {
        return hibStorage.haveUserExistence(login);
    }

    public UserEntity getUser(String login) {
        return hibStorage.getUser(login);
    }

    public List<MessageEntity> getUserMessages(String login) {
        return hibStorage.getUserMessages(login);
    }

    public boolean checkPassword(String login, String password) {
        return hibStorage.checkPassword(login, password);
    }


}
