package project.storages.db;

import project.dto.Pageable;
import project.entity.AuditUserEntity;
import project.entity.MessageEntity;
import project.entity.UserEntity;
import project.storages.api.IChatStorageWithAudit;
import project.utils.DBInitializer;
import project.utils.EntityFactoryInitializer;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DBChatStorageWithAudit implements IChatStorageWithAudit {
    private DBStorage dbStorage = DBStorage.getDatabaseStorage();
    private DBAuditStorage dbAuditStorage = DBAuditStorage.getDatabaseAuditStorage();
    private static DBChatStorageWithAudit dbChatStorageWithAudit = new DBChatStorageWithAudit();
    private DataSource dataSource;

    private DBChatStorageWithAudit() {
        dataSource = DBInitializer.getInstance().getDataSource();
    }

    public static DBChatStorageWithAudit getDBChatStorageWithAudit() {
        return dbChatStorageWithAudit;
    }


    public void addUser(UserEntity user, AuditUserEntity auditUserEntity) {
        try (Connection connection = dataSource.getConnection()) {
            try {
                connection.setAutoCommit(false);
                dbStorage.addUser(user, connection);
                UserEntity userAfterSave = dbStorage.getUser(user.getLogin(), connection);
                auditUserEntity.setUserAudit(userAfterSave);
                auditUserEntity.setAuthor(userAfterSave);
                dbAuditStorage.addAudit(auditUserEntity, connection);
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addMessage(MessageEntity message, AuditUserEntity auditUserEntity) {
        try (Connection connection = dataSource.getConnection()) {
            try {
                connection.setAutoCommit(false);
                dbStorage.addMessage(message, connection);
                auditUserEntity.setUserAudit(dbStorage
                        .getUser(message.getLoginSender(), connection));
                auditUserEntity.setAuthor(dbStorage
                        .getUser(message.getLoginSender(), connection));
                dbAuditStorage.addAudit(auditUserEntity, connection);
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<AuditUserEntity> getUserAudits(UserEntity userEntity) {
        Connection connection = null;
        List<AuditUserEntity> auditUserEntities = null;
        try {
            connection = dataSource.getConnection();
            auditUserEntities = dbAuditStorage.readAudit(userEntity, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return auditUserEntities;
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
        UserEntity user = null;
        try (Connection connection = dataSource.getConnection()) {
            user = dbStorage.getUser(login, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<MessageEntity> getUserMessages(UserEntity userEntity, Pageable pageable) {
        return dbStorage.getUserMessages(userEntity,pageable);
    }

    public boolean checkPassword(String login, String password) {
        return dbStorage.checkPassword(login, password);
    }

}
