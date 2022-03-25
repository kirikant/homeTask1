package project.storages.db;

import project.entity.AuditUserEntity;
import project.entity.MessageEntity;
import project.entity.UserEntity;
import project.storages.api.IChatStorageWithAudit;

import java.util.List;

public class DBChatStorageWithAudit implements IChatStorageWithAudit {
    private DBStorage dbStorage = DBStorage.getDatabaseStorage();
    private DBAuditStorage dbAuditStorage = DBAuditStorage.getDatabaseAuditStorage();
    private static DBChatStorageWithAudit dbChatStorageWithAudit = new DBChatStorageWithAudit();

    public static DBChatStorageWithAudit getDBChatStorageWithAudit() {
        return dbChatStorageWithAudit;
    }

    public void addUser(UserEntity user, AuditUserEntity auditUserEntity) {
        dbStorage.addUser(user);
        dbAuditStorage.addAudit(auditUserEntity);
    }

    public void addMessage(MessageEntity message, AuditUserEntity auditUserEntity) {
        dbStorage.addMessage(message);
        dbAuditStorage.addAudit(auditUserEntity);
    }

    public List<AuditUserEntity> getUserAudits(String login){
       return dbAuditStorage.readAudit(login);
    }
    public long getUsersNumber() {
        return dbStorage.getUsersNumber();
    }

    public long getMessagesNumber() {
        return dbStorage.getMessagesNumber();
    }

    public boolean haveUserExistence(String login) {
        return dbStorage.haveUserExistence(login);
    }

    public UserEntity getUser(String login) {
        return dbStorage.getUser(login);
    }

    public List<MessageEntity> getUserMessages(String login) {
        return dbStorage.getUserMessages(login);
    }

    public boolean checkPassword(String login, String password) {
        return dbStorage.checkPassword(login, password);
    }

}
