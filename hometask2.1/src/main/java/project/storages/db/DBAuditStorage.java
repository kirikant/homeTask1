package project.storages.db;

import project.entity.AuditUserEntity;
import project.entity.UserEntity;
import project.storages.api.IAuditStorage;
import project.storages.api.IStorage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBAuditStorage implements IAuditStorage<Connection> {
    private static DBAuditStorage databaseDBStorage = new DBAuditStorage();
    private IStorage<Connection> dbStorage = DBStorage.getDatabaseStorage();

    private DBAuditStorage() {
    }

    public static DBAuditStorage getDatabaseAuditStorage() {
        return databaseDBStorage;
    }


    public void addAudit(AuditUserEntity auditUserEntity, Connection connection) {
        try (PreparedStatement preparedStatement =connection
                .prepareStatement(Commands.ADD_AUDIT_USER.value)) {
            preparedStatement.setObject(1, auditUserEntity.getDtCreate());
            preparedStatement.setObject(2, auditUserEntity.getText());
            preparedStatement.setLong(3, auditUserEntity.getAuthor().getId());
            preparedStatement.setLong(4, auditUserEntity.getUserAudit().getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<AuditUserEntity> readAudit(UserEntity userEntity,Connection connection) {
        List<AuditUserEntity> auditUserEntities = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(Commands.GET_USER_AUDITS.value)) {
            preparedStatement.setLong(1, dbStorage
                    .getUser(userEntity.getLogin(),connection).getId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    auditUserEntities.add(new AuditUserEntity(resultSet.getLong(1),
                            ((java.sql.Timestamp) (resultSet.getObject(2)))
                                    .toLocalDateTime(),
                            resultSet.getString(3),
                            dbStorage.getUser(userEntity.getLogin(),connection),
                            dbStorage.getUser(userEntity.getLogin(),connection)));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return auditUserEntities;
    }

    private enum Commands {
        ADD_AUDIT_USER("INSERT INTO audits VALUES (nextval('hibernate_sequence'),?,?,?,?)"),
        GET_USER("SELECT * FROM users WHERE login=?"),
        GET_USER_AUDITS("SELECT * FROM audits WHERE useraudit_id=?");

        String value;

        Commands(String value) {
            this.value = value;
        }
    }
}
